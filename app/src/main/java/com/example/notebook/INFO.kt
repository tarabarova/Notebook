package com.example.notebook




import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

import androidx.appcompat.app.AppCompatActivity
import com.example.notebook.R

class INFO: AppCompatActivity() {
    val dbHelper=DBHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info)

        val firstname=findViewById<EditText>(R.id.infoPersonName)
        val lastname=findViewById<EditText>(R.id.infolastname)
        val bday=findViewById<EditText>(R.id.infobday)
        val number=findViewById<EditText>(R.id.infonumber)
        val id = intent.getLongExtra(MainActivity.EXTRA_ID, 0)

        val person = dbHelper.getById(id)
        firstname.setText(person?.firstName)
        lastname.setText(person?.lastName)
        bday.setText(person?.databirth)
        number.setText(person?.number)



        val buttonbACK = findViewById<Button>(R.id.button2)
        buttonbACK.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
    }
        val buttonEdit = findViewById<Button>(R.id.button3)
        buttonEdit.setOnClickListener {

            val intent = Intent(this, editACTIVITY::class.java)
            startActivity(intent)
        }

        val buttonDelete = findViewById<Button>(R.id.button4)
        buttonDelete.setOnClickListener {
            val person_id=intent.getLongExtra(MainActivity.EXTRA_ID,-1L)
            if (person_id==-1L){
                return@setOnClickListener
            }
            dbHelper.remove(person_id)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
}}