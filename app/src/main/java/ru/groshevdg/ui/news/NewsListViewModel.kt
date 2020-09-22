package ru.groshevdg.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.groshevdg.models.ui.InnerSelectorItem
import ru.groshevdg.models.ui.NewsListItems
import ru.groshevdg.models.usecase.NewItem
import ru.groshevdg.usecase.NewsUseCase
import javax.inject.Inject
import kotlin.random.Random

class NewsListViewModel @Inject constructor(private val useCase: NewsUseCase) : ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(Dispatchers.Default + viewModelJob)
    private val _newsLiveData = MutableLiveData<List<NewsListItems>>()
    private val selectedChannels: MutableSet<String> = HashSet()
    private val loadedList: MutableList<NewItem> = ArrayList()
    private val channelList: MutableList<NewItem.Channel> = ArrayList()
    private var selectorItem: NewsListItems.SelectorItem? = null
    private val defaultUiList: MutableList<NewsListItems> = ArrayList()
    val newsLiveData: LiveData<List<NewsListItems>> = _newsLiveData

    fun loadNews() {
        viewModelScope.launch {
            loadedList.clear()
            channelList.clear()
            defaultUiList.clear()
            selectorItem = null

            loadedList.addAll(useCase.getNews())

            val newsList = mutableListOf<NewsListItems.NewItem>()

            for (value in loadedList) {
                if (value is NewItem.Channel) channelList.add(value)
                else newsList.add(NewsListItems.NewItem(value as NewItem.New))
            }

            val itemsList = mutableListOf<NewsListItems>()
             if (channelList.size > 1) {
                 selectorItem = NewsListItems.SelectorItem(channelList.map { InnerSelectorItem(
                     selectorName = it.title,
                     isLoaded = it.isChannelLoaded,
                     isSelected = false) })
                 itemsList.add(selectorItem!!)
             }

            setRandomNewsList(newsList, itemsList)
            defaultUiList.addAll(itemsList)

            _newsLiveData.postValue(defaultUiList)
        }
    }

    private fun setRandomNewsList(
        newsList: MutableList<NewsListItems.NewItem>,
        itemsList: MutableList<NewsListItems>
    ) {
        for (i in 0..newsList.size) {
            if (newsList.size != 0) {
                val randomIndex = Random.nextInt(0, newsList.size)
                itemsList.add(newsList[randomIndex])
                newsList.removeAt(randomIndex)
            }
        }
    }

    fun sortAndShowListWithCategory(category: String) {
        if (selectedChannels.isEmpty()) {
            _newsLiveData.postValue(defaultUiList)
        }
        else {
            val itemList = mutableListOf<NewsListItems>()
            itemList.add(selectorItem!!)

            for (_category in selectedChannels) {
                for (item in defaultUiList) {
                    if ((item is NewsListItems.NewItem) && item.new.category?.contains(_category, true) == true) {
                        itemList.add(item)
                    }
                }
            }
            _newsLiveData.postValue(itemList)
        }
    }

    fun showSelectedNew(link: String) {

    }

    fun setIsChannelSelected(category: String) {
        if (selectedChannels.contains(category)) {
            selectedChannels.remove(category)
        }
        else {
            selectedChannels.add(category)
        }
    }
}