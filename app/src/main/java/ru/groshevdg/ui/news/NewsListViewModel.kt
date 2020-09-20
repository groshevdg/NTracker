package ru.groshevdg.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.groshevdg.models.ui.NewsListItems
import ru.groshevdg.usecase.NewsUseCase
import javax.inject.Inject

class NewsListViewModel @Inject constructor(private val useCase: NewsUseCase) : ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(Dispatchers.Default + viewModelJob)
    private val _newsLiveData = MutableLiveData<List<NewsListItems>>()
    val newsLiveData: LiveData<List<NewsListItems>> = _newsLiveData

    fun loadNews() {
        viewModelScope.launch {
            val loadedList = useCase.getNews()
            val itemsList = mutableListOf<NewsListItems>()
            _newsLiveData.postValue(loadedList.map { NewsListItems.NewItem(it) })
        }
    }
}