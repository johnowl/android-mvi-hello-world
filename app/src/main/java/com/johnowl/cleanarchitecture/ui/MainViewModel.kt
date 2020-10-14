package com.johnowl.cleanarchitecture.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.johnowl.cleanarchitecture.model.BlogPost
import com.johnowl.cleanarchitecture.repository.BlogPostRepository
import com.johnowl.cleanarchitecture.util.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val blogPostRepository: BlogPostRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<BlogPost>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<BlogPost>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when(mainStateEvent) {
                is MainStateEvent.GetBlogPostEvents -> {
                    blogPostRepository.getBlogPosts()
                        .onEach {
                            _dataState.value = it
                        }
                        .launchIn(viewModelScope)
                }

                is MainStateEvent.None -> {
                    // who cares
                }
            }
        }
    }
}

sealed class MainStateEvent {
    object GetBlogPostEvents: MainStateEvent()
    object None: MainStateEvent()
}
