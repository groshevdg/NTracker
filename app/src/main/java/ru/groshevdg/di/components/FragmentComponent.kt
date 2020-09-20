package ru.groshevdg.di.components

import dagger.Component
import ru.groshevdg.di.annotation.FragmentScope
import ru.groshevdg.di.modules.ViewModelModule
import ru.groshevdg.ui.news.NewsListFragment

@FragmentScope
@Component(dependencies = [ActivityComponent::class], modules = [ViewModelModule::class])
interface FragmentComponent {

    fun inject(fragment: NewsListFragment)
}