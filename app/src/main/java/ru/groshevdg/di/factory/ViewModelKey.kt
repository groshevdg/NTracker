package ru.groshevdg.di.factory

import androidx.lifecycle.ViewModel
import dagger.MapKey
import ru.groshevdg.di.annotation.ActivityScope
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
@ActivityScope
annotation class ViewModelKey(val value: KClass<out ViewModel>)