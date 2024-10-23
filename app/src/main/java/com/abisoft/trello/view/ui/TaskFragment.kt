package com.abisoft.trello.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abisoft.trello.R
import com.abisoft.trello.model.RetrofitInstance
import com.abisoft.trello.model.TokenManager
import com.abisoft.trello.model.repasitory.TaskRepository
import com.abisoft.trello.viewModel.task.TaskAdapter
import com.abisoft.trello.viewModel.task.TaskViewModel
import com.abisoft.trello.viewModel.task.TaskViewModelFactory

class TasksFragment : Fragment() {
    // Tokenni olish
    val token = TokenManager(requireContext()).getToken() // Tokenni olish

    val response = RetrofitInstance.taskService
    private val taskViewModel: TaskViewModel by viewModels {
        TaskViewModelFactory(TaskRepository(RetrofitInstance.taskService, TokenManager(requireContext())))
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TaskAdapter // Adapterni albatta yaratishingiz kerak

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = TaskAdapter() // TaskAdapter ni yarating
        recyclerView.adapter = adapter

        // Topshiriqlarni olish
        taskViewModel.fetchTasks()
        observeTasks()

        return view
    }

    private fun observeTasks() {
        taskViewModel.tasks.observe(viewLifecycleOwner, Observer { tasks ->
            if (tasks != null) {
                adapter.submitList(tasks) // Adapterga topshiriqlarni berish
            } else {
                Toast.makeText(requireContext(), "Failed to load tasks", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
