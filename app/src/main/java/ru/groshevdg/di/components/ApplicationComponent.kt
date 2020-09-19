package ru.groshevdg.di.components

import dagger.Component
import ru.groshevdg.App
import ru.groshevdg.di.modules.RetrofitModule
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class])
interface ApplicationComponent {

    fun inject(application: App)
}