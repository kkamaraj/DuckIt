package com.example.duckit.viewmodel

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duckit.datamodel.DuckItInfo
import com.example.duckit.repository.DuckItRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DuckItViewModel @Inject constructor(
    private val duckItRepository: DuckItRepository
): ViewModel() {
    private val _duckItInfoList = emptyList<DuckItInfo>().toMutableStateList()
    val duckItInfoList: List<DuckItInfo>
        get() = _duckItInfoList

    fun initDuckItInfoList() {
        viewModelScope.launch {
            _duckItInfoList.clear()
            _duckItInfoList.addAll(duckItRepository.getDuckItInfo().toMutableStateList())
        }
    }

    fun onUpvoteClick(id: String) {
        duckItInfoList.find { it.id == id }?. let {
            it.upvotes += 1
        }
        // TODO call repository to update upvote count
    }

    fun onDownvoteClick(id: String) {
        duckItInfoList.find { it.id == id }?. let {
            it.upvotes -= 1
        }
        // TODO call repository to update upvote count
    }
}

// test data for UI layer
fun getDuckItInfoList() = List(30) { i ->
    DuckItInfo(
        id = "Eg8KBFBvc3QQgICAuNPXhwo$i",
        headline = "isaac toast",
        image = "https://howtodrawforkids.com/wp-content/uploads/2024/10/Draw-a-duck-step-9.jpeg",
        upvotes = 10
    )
}
