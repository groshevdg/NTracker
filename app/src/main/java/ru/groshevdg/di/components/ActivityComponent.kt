package ru.groshevdg.di.components

import android.content.Context
import dagger.Component
import ru.groshevdg.data.network.NewsApiService
import ru.groshevdg.ui.ApplicationActivity
import ru.groshevdg.di.annotation.ActivityScope
import ru.groshevdg.di.modules.ActivityModule
import ru.groshevdg.di.modules.NavModule
import ru.groshevdg.di.modules.UtilsModule

@ActivityScope
@Component(dependencies = [ApplicationComponent::class],
    modules = [NavModule::class, ActivityModule::class, UtilsModule::class])
interface ActivityComponent {
    fun apiService() : NewsApiService
    fun context() : Context

    fun inject(activity: ApplicationActivity)
}