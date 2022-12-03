package com.example.notebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    companion object {
        const val START_CREATE_CODE = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = mutableListOf<Contact>()
        val adapter = RecyclerAdapter(list)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val buttonAdd = findViewById<Button>(R.id.button_main)
// реакция на нажатие
        buttonAdd.setOnClickListener {
            // добавляем элемент в список
            val editText = findViewById<EditText>(R.id.editText)
            val button = findViewById<Button>(R.id.button_main)

            list.add(editText.text.toString())
            // извещаем адаптер об изменениях
            adapter.notifyItemInserted(list.lastIndex)
        }




// у нас будет линейный список
        recyclerView.layoutManager = LinearLayoutManager(this)
// прикручиваем адаптер к RecyclerView
        recyclerView.adapter = adapter
    }


}
