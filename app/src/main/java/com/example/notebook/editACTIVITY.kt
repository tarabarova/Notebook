package com.example.notebook

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
        val firstnameEdit = findViewById<EditText>(R.id.editNAME)
        val lastnameEdit = findViewById<EditText>(R.id.editLastname)
        val bdayEdit = findViewById<EditText>(R.id.editBday)
        val numberEdit = findViewById<EditText>(R.id.editNumber)
        val id = intent.getLongExtra(MainActivity.EXTRA_ID, 0)
        println(id)

        val person = dbHelper.getById(id)
        firstnameEdit.setText(person?.firstName)
        lastnameEdit.setText(person?.lastName)
        bdayEdit.setText(person?.databirth)
        numberEdit.setText(person?.number)

        buttonSave.setOnClickListener {
            // так можно менять текст кнопки
            val firstname = firstnameEdit.text.toString()
            val lastname = lastnameEdit.text.toString()
            val bday = bdayEdit.text.toString()
            val phonenumber = numberEdit.text.toString()
            if (id == 0L) {
                dbHelper.add(lastname, firstname, bday, phonenumber)
            } else {
                dbHelper.update(id, lastname, firstname, bday, phonenumber)
            }

            val returnIntent = Intent(this, MainActivity::class.java)
            startActivity(returnIntent)
        }
    }
}