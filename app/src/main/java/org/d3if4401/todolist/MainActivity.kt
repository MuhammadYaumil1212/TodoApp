package org.d3if4401.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.d3if4401.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        todoAdapter = TodoAdapter(mutableListOf())
        todoItems.adapter = todoAdapter
        todoItems.layoutManager = LinearLayoutManager(this)

        buttonTodo.setOnClickListener{
            val title = todoTitle.toString()
            if(title.isNotEmpty()){
                val todo = Todo(title)
                todoAdapter.addTodo(todo)
                todoTitle.text.clear()
            }
        }
        deleteButton.setOnClickListener { todoAdapter.deleteDoneTodo() }
    }
}