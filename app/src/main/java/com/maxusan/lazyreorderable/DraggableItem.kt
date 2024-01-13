package com.maxusan.lazyreorderable

import java.util.UUID

data class DraggableItem(
    val id: String = UUID.randomUUID().toString(),
    val text: String
)
