package com.myapp.todoapp.presentation.add_edit_todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.todoapp.domain.model.Todo
import com.myapp.todoapp.domain.repository.TodoRepository
import com.myapp.todoapp.uitl.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val repository: TodoRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var todo by mutableStateOf<Todo?>(null)
    private set

    var title by mutableStateOf("")
    private set

    var description by mutableStateOf("")
    private set

    var eventFlow = MutableSharedFlow<UiEvent>()
        private set 
    
    init {
        val todoId = savedStateHandle.get<Int>("todoId")!!
        if(todoId != -1 ) {
            viewModelScope.launch {
                repository.getTodoById(todoId)?.let { todo ->
                    title = todo.title
                    description = todo.description ?: ""
                    this@AddEditTodoViewModel.todo = todo
                }
            }
        }
    }

    fun onEvent(event: AddEditTodoEvent) {
        when (event) {
            is AddEditTodoEvent.OnTitleChange -> {
                title = event.title
            }
            is AddEditTodoEvent.OnDescriptionChange -> {
                description = event.description
            }
            is AddEditTodoEvent.OnSaveTodoClick -> {
                viewModelScope.launch {
                    if(title.isBlank()) {
                        eventFlow.emit(UiEvent.ShowSnackbar(
                            message = "The title can't be empty"
                        ))
                        return@launch
                    }
                    repository.insertTodo(
                        Todo(
                            title = title,
                            description = if(description.isEmpty()) null else description,
                            isDone = todo?.isDone ?: false,
                            id = todo?.id
                        )
                    )
                    eventFlow.emit(UiEvent.PopBackStack)
                }
            }
        }
    }
}