package ru.groshevdg.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.groshevdg.models.ui.InnerSelectorItem
import ru.groshevdg.models.ui.NewsListItems
import ru.groshevdg.models.usecase.NewItem
import ru.groshevdg.usecase.NewsUseCase
import ru.groshevdg.utils.SharedPrefsUtils
import javax.inject.Inject

class NewsListViewModel @Inject constructor(private val useCase: NewsUseCase,
                                            private val prefsUtils: SharedPrefsUtils) : ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(Dispatchers.Default + viewModelJob)
    private val _newsLiveData = MutableLiveData<List<NewsListItems>>()
    val newsLiveData: LiveData<List<NewsListItems>> = _newsLiveData

    fun loadNews() {
        viewModelScope.launch {
            val loadedList = useCase.getNews()
            val channelList = mutableListOf<NewItem.Channel>()
            val newsList = mutableListOf<NewsListItems.NewItem>()

            for (value in loadedList) {
                if (value is NewItem.Channel) channelList.add(value)
                else newsList.add(NewsListItems.NewItem(value as NewItem.New))
            }

            val itemsList = mutableListOf<NewsListItems>()
             if (channelList.size > 1) {
                 itemsList.add(NewsListItems.SelectorItem(channelList.map { InnerSelectorItem(selectorName = it.title,
                     isLoaded = it.isChannelLoaded,
                     isEnableLoading = prefsUtils.getSourcePrefByKey(it.title)) }))
             }

            itemsList.addAll(newsList)
            _newsLiveData.postValue(itemsList)
        }
    }
}