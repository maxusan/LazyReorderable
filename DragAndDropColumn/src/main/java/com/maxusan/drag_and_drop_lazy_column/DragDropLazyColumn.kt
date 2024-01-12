package com.maxusan.drag_and_drop_lazy_column

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun <T> DragDropLazyColumn(
    modifier: Modifier = Modifier,
    items: List<T>,
    onMove: (Int, Int) -> Unit,
    onDragEnd: () -> Unit,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    verticalArrangement: Arrangement.HorizontalOrVertical = Arrangement.spacedBy(16.dp),
    contentPadding: PaddingValues = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
    itemContent: (@Composable LazyItemScope.(draggingModifier: Modifier, item: T) -> Any),
    footerContent: (@Composable LazyItemScope.() -> Unit)? = null,
) {
    val scope = rememberCoroutineScope()

    var overscrollJob by remember { mutableStateOf<Job?>(null) }

    val listState = rememberLazyListState()
    val dragDropListState = rememberDragDropListState(
        onMove = onMove,
        onDragEnd = onDragEnd,
        lazyListState = listState
    )

    LazyColumn(
        modifier = modifier
            .pointerInput(Unit) {
                detectDragGesturesAfterLongPress(onDrag = { change, offset ->
                    change.consume()
                    dragDropListState.onDrag(offset)

                    if (overscrollJob?.isActive == true) return@detectDragGesturesAfterLongPress

                    dragDropListState
                        .checkForOverScroll()
                        .takeIf { it != 0f }
                        ?.let { scroll ->
                            overscrollJob = scope.launch {
                                dragDropListState.lazyListState.scrollBy(scroll)
                            }
                        } ?: run { overscrollJob?.cancel() }

                },
                    onDragStart = { offset -> dragDropListState.onDragStart(offset) },
                    onDragEnd = { dragDropListState.onDragInterrupted() },
                    onDragCancel = { dragDropListState.onDragInterrupted() })
            },
        state = dragDropListState.lazyListState,
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = verticalArrangement,
        contentPadding = contentPadding
    ) {
        itemsIndexed(
            items = items,
            key = { index, _ -> index },
            contentType = { _, _ -> DragAndDropItemType.Draggable }) { index, item ->
            itemContent(
                Modifier.composed {
                    val scale =
                        if (index == dragDropListState.currentIndexOfDraggedItem) 1.05f else 1f
                    val zIndex =
                        if (index == dragDropListState.currentIndexOfDraggedItem) 1f else 0f

                    val offsetOrNull = dragDropListState.elementDisplacement.takeIf {
                        index == dragDropListState.currentIndexOfDraggedItem
                    }
                    val animateOffset by animateFloatAsState(
                        targetValue = offsetOrNull ?: 0f,
                        label = ""
                    )
                    Modifier
                        .graphicsLayer {
                            translationY =
                                if (dragDropListState.currentIndexOfDraggedItem == index)
                                    offsetOrNull ?: 0f
                                else animateOffset
                        }
                        .scale(scale)
                        .zIndex(zIndex)
                },
                item
            )
        }
        footerContent?.also {
            item(
                content = it,
                contentType = DragAndDropItemType.Footer
            )
        }
    }
}