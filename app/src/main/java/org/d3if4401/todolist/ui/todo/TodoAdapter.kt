package org.d3if4401.todolist.ui.todo

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*
import org.d3if4401.todolist.R
import org.d3if4401.todolist.model.Todo

class TodoAdapter(private val todos: MutableList<Todo>)
    : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }
    fun addTodo(todo: Todo){
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }
    fun deleteDoneTodo(){
        todos.removeAll { todo -> todo.isChecked }
        notifyDataSetChanged()
    }
    private fun toggleStrikeThrought(todoItem: TextView, isChecked: Boolean){
        if(isChecked) todoItem.paintFlags  = todoItem.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        else todoItem.paintFlags = todoItem.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = todos[position]
        holder.itemView.apply {
            TodoItem.text = currentTodo.title
            checkBox.isChecked = currentTodo.isChecked

            toggleStrikeThrought(TodoItem, currentTodo.isChecked)
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrought(TodoItem, isChecked)
                currentTodo.isChecked = !currentTodo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}