package com.tapaafandi.core.di

import com.tapaafandi.core.BuildConfig
import com.tapaafandi.core.data.network.retrofit.RetrofitDicoplayNetworkApi
import com.tapaafandi.core.uitls.Constants.BASE_URL
import com.tapaafandi.core.uitls.Constants.HOST_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun providesHttpClient(): OkHttpClient {

        val certificatePinner = CertificatePinner.Builder()
            .add(HOST_NAME, "sha256/aibC8oPHcGrFgJi2WmuIncc4TkOr+XPZfupJR+2yZ9g=")
            .add(HOST_NAME, "sha256/8kGWrpQHhmc0jwLo43RYo6bmqtHgsNxhARjM5yFCe/w=")
            .add(HOST_NAME, "sha256/gI1os/q0iEpflxrOfRBVDXqVoWN3Tz7Dav/7IT++THQ=")
            .build()

        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        if (BuildConfig.DEBUG) {
                            setLevel(HttpLoggingInterceptor.Level.BODY)
                        }
                    }
            )
            .certificatePinner(certificatePinner)
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofitDicoplayNetworkApi(retrofit: Retrofit): RetrofitDicoplayNetworkApi {
        return retrofit.create(RetrofitDicoplayNetworkApi::class.java)
    }
}