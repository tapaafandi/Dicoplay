package com.tapaafandi.dicoplay.sync.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.tapaafandi.dicoplay.domain.repository.GameRepository
import com.tapaafandi.dicoplay.sync.initializers.syncForegroundInfo
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted workerParams: WorkerParameters,
    private val gameRepository: GameRepository,
) : CoroutineWorker(context, workerParams) {

    override suspend fun getForegroundInfo(): ForegroundInfo = context.syncForegroundInfo()

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val syncedSuccessfully = gameRepository.synchronize()
        if (syncedSuccessfully) {
            Result.success()
        } else {
            Result.retry()
        }
    }

    companion object {
        fun startUpSyncWork() = OneTimeWorkRequestBuilder<DelegatingWorker>()
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .setInputData(SyncWorker::class.delegatedData())
            .build()
    }
}