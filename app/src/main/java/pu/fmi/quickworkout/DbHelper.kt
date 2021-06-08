package pu.fmi.quickworkout

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf
import androidx.core.database.getDoubleOrNull
import java.lang.Exception

class DbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_HISTORY_TABLE = ("CREATE TABLE " +
                TABLE_HISTORY + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_HEIGHT + " REAL," +
                COLUMN_WEIGHT + " REAL," +
                COLUMN_BMI + " REAL," +
                COLUMN_BMI_STATUS + " TEXT," +
                COLUMN_DATE + " TEXT" + ")")
        db?.execSQL(CREATE_HISTORY_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_HISTORY")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "QuickWorkout.db"
        private const val TABLE_HISTORY = "history"

        private const val COLUMN_ID = "_id"
        private const val COLUMN_HEIGHT = "height"
        private const val COLUMN_WEIGHT = "weight"
        private const val COLUMN_BMI = "bmi"
        private const val COLUMN_BMI_STATUS = "bmi_status"
        private const val COLUMN_DATE = "date"
    }

    fun addRecord(record: BMIRecord) {

        val values = ContentValues()
        values.put(COLUMN_HEIGHT, record.height)
        values.put(COLUMN_WEIGHT, record.weight)
        values.put(COLUMN_BMI, record.bmi)
        values.put(COLUMN_BMI_STATUS, record.bmiStatus)
        values.put(COLUMN_DATE, record.date)


        val db = this.writableDatabase

        db.insert(TABLE_HISTORY, null, values)
        db.close()
    }

    fun getAllRecords(): ArrayList<BMIRecord> {
        val recordsList: ArrayList<BMIRecord> = ArrayList()
        val query = "SELECT * FROM $TABLE_HISTORY"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(query, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(query)
            return ArrayList()
        }

        var id: Int = 0
        var weight: Float? = null
        var height: Float? = null
        var bmiStatus: String? = null
        var bmi: Float? = null
        var date: String? = null

        if (cursor.moveToFirst()) {
            do {

                id = cursor.getInt(cursor.getColumnIndex("_id"))
                weight = cursor.getFloat(cursor.getColumnIndex("weight"))
                height = cursor.getFloat(cursor.getColumnIndex("height"))
                bmiStatus = cursor.getString(cursor.getColumnIndex("bmi_status"))
                bmi = cursor.getFloat(cursor.getColumnIndex("bmi"))
                date = cursor.getString(cursor.getColumnIndex("date"))
                val record = BMIRecord(id,weight,height,bmiStatus,bmi,date)
                recordsList.add(record)

            } while (cursor.moveToNext())
        }

        db.close()
        return recordsList

    }

    fun deleteRecordById(id: Int): Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_ID,id)

        val success = db.delete(TABLE_HISTORY, "_id=$id",null)
        db.close()
        return success
    }


}