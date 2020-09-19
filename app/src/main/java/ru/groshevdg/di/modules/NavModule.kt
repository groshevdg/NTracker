package ru.groshevdg.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.groshevdg.di.annotation.ActivityScope
import ru.groshevdg.navigation.Navigator

@Module
class NavModule {

    @Provides
    @ActivityScope
    fun provideNavigator(context: Context) : Navigator {
        return Navigator(context)
    }
}