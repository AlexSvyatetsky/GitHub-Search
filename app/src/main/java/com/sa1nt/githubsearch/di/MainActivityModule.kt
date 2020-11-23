package com.sa1nt.githubsearch.di

import com.sa1nt.githubsearch.data.remote.DataManager
import com.sa1nt.githubsearch.ui.MainPresenter
import dagger.Module
import dagger.Provides


@Module
class MainActivityModule {

    @Provides
    internal fun provideMainPresenter(dataManager: DataManager): MainPresenter {
        return MainPresenter(dataManager)
    }

}