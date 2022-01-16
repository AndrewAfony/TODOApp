package com.myapp.todoapp.presentation.todo_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapp.todoapp.domain.model.Todo

@Composable
fun TodoItem(
    todo: Todo,
    onEvent: (TodoListEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    var paddingOfText by remember { mutableStateOf(PaddingValues())}

    if (todo.description != "" && todo.description != null)
        paddingOfText = PaddingValues(vertical = 8.dp)

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
    ){
        Row(
            modifier = Modifier.padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Checkbox(
                checked = todo.isDone,
                onCheckedChange = { isChecked ->
                    onEvent(TodoListEvent.OnDoneChange(todo, isChecked))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(paddingOfText),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = todo.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                if (todo.description != "" && todo.description != null) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = todo.description,
                        fontWeight = FontWeight.Light
                    )
                }
            }
            IconButton(onClick = {
                onEvent(TodoListEvent.OnDeleteTodoClick(todo))
            }) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Delete Todo"
                )
            }
        }
    }


}


@Preview(showBackground = true)
@Composable
fun preview() {
    TodoItem(todo = Todo(title = "One", "Description", false), onEvent = {})
}