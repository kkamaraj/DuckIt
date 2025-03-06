package com.example.duckit.viewmodel

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duckit.repository.DuckItRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DuckItViewModel @Inject constructor(
    private val duckItRepository: DuckItRepository
): ViewModel() {
    val duckItViewDataList: MutableList<DuckItViewData> = emptyList<DuckItViewData>().toMutableStateList()

    fun initDuckItInfoList() {
        viewModelScope.launch {
            val result = duckItRepository.getDuckItInfo()
            if(result.isSuccess) {
                val duckItInfoList = result.getOrDefault(emptyList()).toMutableStateList()
                duckItInfoList.forEach {
                    duckItViewDataList.add(
                        DuckItViewData(
                            id = it.id,
                            headline = it.headline,
                            image = it.image,
                            upvotes = if (it.upvotes > 0) {
                                mutableIntStateOf(it.upvotes)
                            } else {
                                mutableIntStateOf(0)
                            }
                        )
                    )
                }
            } else {
                duckItViewDataList.clear()
            }
        }
    }

    fun onUpvoteClick(id: String) {
        duckItViewDataList.find { it.id == id }?. let {
            it.upvotes.value += 1
        }
        // TODO call repository to update upvote count
    }

    fun onDownvoteClick(id: String) {
        duckItViewDataList.find { it.id == id }?. let {
            it.upvotes.value -= 1
        }
        // TODO call repository to update upvote count
    }
}
