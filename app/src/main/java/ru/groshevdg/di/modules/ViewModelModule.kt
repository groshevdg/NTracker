package ru.groshevdg.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.groshevdg.di.factory.ViewModelKey
import ru.groshevdg.ui.news.NewsListViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsListViewModel::class)
    internal abstract fun bindNewsListViewModel(newsListViewModel: NewsListViewModel) : ViewModel
}