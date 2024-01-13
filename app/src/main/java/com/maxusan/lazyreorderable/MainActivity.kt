package com.maxusan.lazyreorderable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.maxusan.drag_and_drop_lazy_column.DragDropLazyColumn
import com.maxusan.lazyreorderable.ui.theme.LazyReorderableTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel by viewModels()
            val list = viewModel.draggableListState
            LazyReorderableTheme {
                DragDropLazyColumn(
                    items = list.toList(),
                    onMove = { from, to ->
                        viewModel.moveItem(from, to)
                    },
                    onDragEnd = {
                        // Implement onDragEnd logic here
                    },
                    itemContent = { draggingModifier, item ->
                        DraggableListItem(
                            modifier = draggingModifier
                                .fillMaxWidth()
                                .height(64.dp),
                            item = item
                        )
                    },
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(
                        horizontal = 16.dp,
                        vertical = 16.dp
                    )
                )
            }
        }
    }

    @Composable
    fun DraggableListItem(
        modifier: Modifier,
        item: DraggableItem,
    ) {
        Card(modifier = modifier, colors = CardDefaults.cardColors(containerColor = DarkGray)) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = item.text, modifier = Modifier, color = White)
            }
        }
    }
}
