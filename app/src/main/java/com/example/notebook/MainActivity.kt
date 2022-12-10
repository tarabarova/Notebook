package com.example.notebook

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    companion object {
        const val START_CREATE_CODE = 1
    }

    private val dbHelper = DBHelper(this)

    private val list = mutableListOf<Contact>()

    lateinit var adapter: RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        list.addAll(dbHelper.getAll())


        // создаём инстанс адаптера, отдаём ему список
        adapter = RecyclerAdapter(list) {

            val intent = Intent(this, INFO::class.java)
            startActivity(intent)
            /*val buttonINFO= findViewById<Button>(R.id.button_info)*/
        }



        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        // у нас будет линейный список
        recyclerView.layoutManager = LinearLayoutManager(this)
        // прикручиваем адаптер к RecyclerView
        recyclerView.adapter = adapter

        val editText: EditText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button_main)
        button.setOnClickListener {

            val intent = Intent(this, editACTIVITY::class.java)
            startActivityForResult(intent, START_CREATE_CODE)


        }




    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == START_CREATE_CODE && resultCode == Activity.RESULT_OK) {
            list.clear()
            list.addAll(dbHelper.getAll())
            adapter.notifyDataSetChanged()
        }

    }
}