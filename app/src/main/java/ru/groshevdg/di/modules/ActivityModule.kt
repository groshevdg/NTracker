package ru.groshevdg.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.groshevdg.ui.ApplicationActivity
import ru.groshevdg.di.annotation.ActivityScope

@Module
class ActivityModule(private val activity: ApplicationActivity) {

    @Provides
    @ActivityScope
    fun provideActivity() : ApplicationActivity {
        return activity
    }

    @Provides
    @ActivityScope
    fun provideContext() : Context {
        return activity
    }
}