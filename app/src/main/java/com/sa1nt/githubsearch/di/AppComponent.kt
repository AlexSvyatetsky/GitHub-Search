package com.sa1nt.githubsearch.di

import android.app.Application
import com.sa1nt.githubsearch.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

@AppScope
@Component(
    modules = [(AndroidInjectionModule::class), (AppModule::class), (DataModule::class),
        (RemoteModule::class), (ActivityBuilder::class)]
)

interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: App)

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}