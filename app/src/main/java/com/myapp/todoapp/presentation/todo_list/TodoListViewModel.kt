package com.myapp.todoapp.presentation.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.todoapp.domain.model.Todo
import com.myapp.todoapp.domain.repository.TodoRepository
import com.myapp.todoapp.uitl.Routes
import com.myapp.todoapp.uitl.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository
): ViewModel() {

    val todos = repository.getTodos()

    var eventFlow = MutableSharedFlow<UiEvent>()
        private set

    private var deletedTodo: Todo? = null

    fun onEvent(event: TodoListEvent){
        when (event) {
            is TodoListEvent.OnTodoClick -> {
                viewModelScope.launch {
                    eventFlow.emit(UiEvent.Navigate(Routes.ADD_EDIT_TODO + "?todoId=${event.todo.id}"))
                }
            }
            is TodoListEvent.OnAddTodoClick -> {
                viewModelScope.launch {
                    eventFlow.emit(UiEvent.Navigate(Routes.ADD_EDIT_TODO))
                }
            }
            is TodoListEvent.OnUndoDeleteClick -> {
                deletedTodo?.let { todo ->
                    viewModelScope.launch {
                        repository.insertTodo(todo)
                    }
                }
            }
            is TodoListEvent.OnDeleteTodoClick -> {
                viewModelScope.launch {
                    deletedTodo = event.todo
                    repository.deleteTodo(event.todo)
                    eventFlow.emit(UiEvent.ShowSnackbar(message = "Todo Deleted", action = "Undo"))
                }
            }
            is TodoListEvent.OnDoneChange -> {
                viewModelScope.launch {
                    repository.insertTodo(
                        event.todo.copy(
                            isDone = event.isDone
                        )
                    )
                }
            }
        }
    }
}