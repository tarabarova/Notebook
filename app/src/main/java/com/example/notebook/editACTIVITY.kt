package com.example.notebook

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class editACTIVITY : AppCompatActivity() {


    val dbHelper = DBHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit)

        val buttonCancel = findViewById<Button>(R.id.buttonCancel)
        buttonCancel.setOnClickListener {
            //finish()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val buttonSave = findViewById<Button>(R.id.button_SAVE)
        buttonSave.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)


        }
        val editTextName = findViewById<EditText>(R.id.editNAME)
        val editTextLast = findViewById<EditText>(R.id.editLastname)
        val editTextBay = findViewById<EditText>(R.id.editBday)
        val editTextPhone = findViewById<EditText>(R.id.editNumber)
        val button = findViewById<Button>(R.id.button_SAVE)
        button.setOnClickListener {
            // так можно менять текст кнопки


            val firstname = editTextName.text.toString()
            val lastname = editTextLast.text.toString()
            val bday = editTextBay.text.toString()
            val phonenumber = editTextPhone.text.toString()
            dbHelper.add(lastname, firstname, bday, phonenumber)

            val returnIntent = Intent()
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }
}