package ru.groshevdg.di.components

import dagger.Component
import ru.groshevdg.ui.ApplicationActivity
import ru.groshevdg.di.annotation.ActivityScope
import ru.groshevdg.di.modules.ActivityModule
import ru.groshevdg.di.modules.NavModule

@ActivityScope
@Component(dependencies = [ApplicationComponent::class],
    modules = [NavModule::class, ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: ApplicationActivity)
}