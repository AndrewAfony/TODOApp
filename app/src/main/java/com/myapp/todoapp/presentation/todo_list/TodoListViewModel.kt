package com.myapp.todoapp.presentation.todo_list

import androidx.lifecycle.ViewModel
import com.myapp.todoapp.domain.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository
): ViewModel() {

    val todos = repository.getTodos()




}