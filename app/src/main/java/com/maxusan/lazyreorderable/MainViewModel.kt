package com.maxusan.lazyreorderable

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {


    val draggableListState = mutableStateListOf(
        DraggableItem(text = "Item 1"),
        DraggableItem(text = "Item 2"),
        DraggableItem(text = "Item 3"),
        DraggableItem(text = "Item 4"),
        DraggableItem(text = "Item 5"),
        DraggableItem(text = "Item 6"),
        DraggableItem(text = "Item 7"),
        DraggableItem(text = "Item 8"),
        DraggableItem(text = "Item 9"),
        DraggableItem(text = "Item 10"),
    )

    fun moveItem(from: Int, to: Int) {
        val item = draggableListState.removeAt(from)
        draggableListState.add(to, item)
    }
}