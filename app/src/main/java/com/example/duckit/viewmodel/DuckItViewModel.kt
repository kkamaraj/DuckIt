package com.example.duckit.viewmodel

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.duckit.datamodel.DuckItInfo
import com.example.duckit.repository.DuckItRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DuckItViewModel @Inject constructor(
    private val duckItRepository: DuckItRepository
): ViewModel() {
    private val _duckItInfoList = getDuckItInfoList().toMutableStateList()
    val duckItInfoList: List<DuckItInfo>
        get() = _duckItInfoList

    fun onUpvoteClick(id: String) {
        duckItInfoList.find { it.id == id }?. let {
            it.updatedUpvoteCount += 1
        }
        // TODO call repository to update upvote count
    }

    fun onDownvoteClick(id: String) {
        duckItInfoList.find { it.id == id }?. let {
            it.updatedUpvoteCount -= 1
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
        upvoteCount = 10
    )
}
