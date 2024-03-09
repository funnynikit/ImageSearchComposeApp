package com.example.searchimageapp.ui.components

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchimageapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    var list: MutableState<MainState> = mutableStateOf(MainState())

    fun getFlowersList(q: String) = viewModelScope.launch {
        list.value = MainState(isLoading = true)

        try {
            val result = mainRepository.getFlowersQuery(q)
            when (result) {
                is Resource.Success -> {
                    result.myData?.hits?.let {
                        list.value = MainState(data = it)
                    }
                }

                is Resource.Error -> {
                    list.value = MainState(error = result.msg.toString())
                }

                is Resource.Loading -> TODO()
            }
        } catch (e: Exception) {
            list.value = MainState(error = "Something went wrong")
        }
    }

}