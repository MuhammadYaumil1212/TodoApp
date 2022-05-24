package org.d3if4401.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.d3if4401.todolist.databinding.ActivityMainBinding
import org.d3if4401.todolist.model.Todo
import org.d3if4401.todolist.ui.todo.TodoAdapter

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
            sanityCheck()
        }
        deleteButton.setOnClickListener { todoAdapter.deleteDoneTodo() }
    }

    private fun sanityCheck(){
        val todoText = binding.todoTextInp.text.toString()
        if(todoText.isNotEmpty()){
            val todo = Todo(todoText)
            todoAdapter.addTodo(todo)
            todoText_inp.text?.clear()
        }else{
            Toast.makeText(this, "Kok kosong ? kamu mau ngapain sih ?",
                Toast.LENGTH_LONG).show()
        }
    }
}