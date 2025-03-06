package com.example.duckit.viewmodel

import androidx.compose.runtime.mutableIntStateOf
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
    private val _duckItInfoList = emptyList<DuckItViewData>().toMutableStateList()
    val duckItInfoList: List<DuckItViewData>
        get() = _duckItInfoList

    fun initDuckItInfoList() {
        viewModelScope.launch {
            _duckItInfoList.clear()
            val duckItInfoList = duckItRepository.getDuckItInfo()
            duckItInfoList.forEach {
                _duckItInfoList.add(DuckItViewData(
                    id = it.id,
                    headline = it.headline,
                    image = it.image,
                    upvotes = if (it.upvotes > 0) {
                        mutableIntStateOf(it.upvotes)
                    } else {
                        mutableIntStateOf(0)
                    }
                ))
            }
        }
    }

    fun onUpvoteClick(id: String) {
        duckItInfoList.find { it.id == id }?. let {
            it.upvotes.value += 1
        }
        // TODO call repository to update upvote count
    }

    fun onDownvoteClick(id: String) {
        duckItInfoList.find { it.id == id }?. let {
            it.upvotes.value -= 1
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
