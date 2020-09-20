package ru.groshevdg.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.groshevdg.di.annotation.ActivityScope
import ru.groshevdg.utils.SharedPrefsUtils

@Module
class UtilsModule(private val context: Context) {

    @Provides
    @ActivityScope
    fun providePrefUtils() : SharedPrefsUtils {
        return SharedPrefsUtils(context)
    }
}