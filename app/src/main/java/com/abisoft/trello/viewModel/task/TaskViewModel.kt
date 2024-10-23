package com.abisoft.trello.viewModel.task
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abisoft.trello.model.data.Task
import com.abisoft.trello.model.repasitory.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {

    private val _tasks = MutableLiveData<List<Task>?>()
    val tasks: LiveData<List<Task>?> get() = _tasks
   
    fun fetchTasks() {
        viewModelScope.launch {
            val result = taskRepository.getAllTasks()
            _tasks.value = result
        }
    }
}
