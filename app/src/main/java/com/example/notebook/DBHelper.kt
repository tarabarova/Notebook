package com.example.notebook

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



class DBHelper(context: Context?) :
SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "tododb"
        const val TABLE_NAME = "todos"
        const val KEY_ID = "id"
        const val KEY_firstName = "firstName"
        const val KEY_lastName = "lastName"
        const val KEY_databirth = "databirth"
        const val KEY_number = "number"

    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE $TABLE_NAME (
                $KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $KEY_firstName TEXT NOT NULL,
                $KEY_lastName TEXT NOT NULL,
                $KEY_databirth TEXT NOT NULL,
                $KEY_number TEXT NOT NULL
            )"""
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun getAll(): List<Contact> {
        val result = mutableListOf<Contact>()
        val database = this.writableDatabase
        val cursor: Cursor = database.query(
            TABLE_NAME, null, null, null,
            null, null, null
        )
        if (cursor.moveToFirst()) {
            val idIndex: Int = cursor.getColumnIndex(KEY_ID)

            val firstnameIndex: Int = cursor.getColumnIndex(KEY_firstName)
            val lastnameIndex: Int = cursor.getColumnIndex(KEY_lastName)
            val bdayIndex: Int = cursor.getColumnIndex(KEY_databirth)
            val phonenumberIndex: Int = cursor.getColumnIndex(KEY_number)

            do {
                val contact = Contact(
                    cursor.getLong(idIndex),
                    cursor.getString(lastnameIndex),
                    cursor.getString(firstnameIndex),
                    cursor.getString(bdayIndex),
                    cursor.getString(phonenumberIndex),

                    )
                result.add(contact)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return result
    }

    fun add(lastname: String, firstname: String, bday: String, phonenumber: String): Long {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        ///
        contentValues.put(KEY_firstName, firstname)
        contentValues.put(KEY_lastName, lastname)
        contentValues.put(KEY_databirth, bday)
        contentValues.put(KEY_number, phonenumber)

        val id = database.insert(TABLE_NAME, null, contentValues)
        close()
        return id
    }

    fun update(id: Long, lastname: String, firstname: String, bday: String, phonenumber: String) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        ///
        contentValues.put(KEY_firstName, firstname)
        contentValues.put(KEY_lastName, lastname)
        contentValues.put(KEY_databirth, bday)
        contentValues.put(KEY_number, phonenumber)

        database.update(TABLE_NAME, contentValues, "$KEY_ID = ?", arrayOf(id.toString()))
        close()
    }

    fun remove(id: Long) {
        val database = this.writableDatabase
        //database.delete(TABLE_NAME, "$KEY_ID = ?", arrayOf(id.toString()))
        database.delete(TABLE_NAME, "$KEY_ID = ?", arrayOf(id.toString()))

        close()
    }

    fun removeAll() {
        val database = this.writableDatabase
        database.delete(TABLE_NAME, null, null)
        close()
    }

}