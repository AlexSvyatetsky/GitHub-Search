package com.sa1nt.githubsearch.di

import com.sa1nt.githubsearch.data.remote.Api
import com.sa1nt.githubsearch.data.remote.DataManager
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class DataModule {
    @Provides
    @AppScope
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Provides
    @AppScope
    fun provideDataManager(
        api: Api
    ): DataManager {
        return DataManager(api)
    }
}