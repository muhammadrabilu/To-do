package com.rabilu.todo.ui.presentation.common

import androidx.compose.animation.VectorConverter
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rabilu.todo.data.local.model.Todo
import com.rabilu.todo.ui.theme.PurpleGrey80

@Composable
fun TodoItem(
    modifier: Modifier = Modifier,
    todo: Todo,
    onClick: () -> Unit,
    onDelete: () -> Unit,
    onComplete: () -> Unit
) {
    Card(
        modifier = modifier, colors = CardDefaults.cardColors(
            containerColor = PurpleGrey80.copy(alpha = 0.2F)
        )
    ) {
        Column(modifier = Modifier
            .padding(16.dp)
            .clickable { onClick() }) {
            Text(
                text = todo.todo!!, style = TextStyle(
                    fontSize = 18.sp, fontWeight = FontWeight.Bold
                )
            )
            MySpacer(height = 16)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(
                                color = if (todo.completed) Color(0xFF059805).copy(
                                    alpha = 0.1F
                                ) else Color.Red.copy(
                                    alpha = 0.1F
                                )
                            )
                            .padding(4.dp)
                            .clickable(enabled = !todo.completed) {
                                onComplete()
                            }

                    ) {
                        Icon(
                            imageVector = if (todo.completed) Icons.Outlined.CheckCircle else Icons.Outlined.Circle,
                            contentDescription = null,
                            tint = if (todo.completed) Color(0xFF059805) else Color.Red.copy(
                                alpha = 0.5F
                            )
                        )
                        Text(
                            text = if (todo.completed) "Completed" else "Uncompleted",
                            color = if (todo.completed) Color(0xFF059805) else Color.Red.copy(
                                alpha = 0.5F
                            ),
                            fontWeight = FontWeight.W600
                        )
                    }


                }

                IconButton(onClick = {
                    onDelete()
                }) {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = null,
                        tint = Color.Red
                    )
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
private fun TodoItemPrev() {
//    TodoItem(todo = Todo(todo = "Learn javascript", completed = true))
}