package ru.groshevdg

import android.app.Application
import ru.groshevdg.data.network.NewsApiService
import ru.groshevdg.di.components.ApplicationComponent
import ru.groshevdg.di.components.DaggerApplicationComponent
import javax.inject.Inject

class App : Application() {
    lateinit var appComponent: ApplicationComponent
    @Inject lateinit var apiService: NewsApiService

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.create()
        appComponent.inject(this)
        instance = this
    }
}