package com.example.storygameapp.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.icu.util.Calendar
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

@SuppressLint("Range")
class dbHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val progressionQuery =
            ("CREATE TABLE IF NOT EXISTS " + PROGRESSION_TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY, " +
                STORY_POS + " INTEGER," +
                COLLECT_FOUND + " INTEGER," +
                COLLECT_NEEDED + " INTEGER"
                +")")

        val settingsQuery =
            ("CREATE TABLE IF NOT EXISTS " + SETTINGS_TABLE_NAME + " ("
                    + SETTINGS_ID + " INTEGER PRIMARY KEY, " +
                    ENABLE_LARGE_FONT + " INTEGER," +
                    ENABLE_SIMPLE_FONT + " INTEGER" +
                    ")")

        db.execSQL(progressionQuery)
        db.execSQL(settingsQuery)
    }


    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE $PROGRESSION_TABLE_NAME")
        onCreate(db)
    }

    private fun getProgressionData(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $PROGRESSION_TABLE_NAME", null)

    }

    private fun getSettingData(): Cursor?{
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $SETTINGS_TABLE_NAME", null)
    }

    fun initSettingsTable(){
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(ENABLE_LARGE_FONT, 0)
        values.put(ENABLE_SIMPLE_FONT, 0)

        Log.i("DB_VAL", values.toString())

        db.insert(SETTINGS_TABLE_NAME, null, values)
        db.close()
    }

    // TODO: Implement this
    private fun initProgressionTable(){
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(ENABLE_LARGE_FONT, 0)
        values.put(ENABLE_SIMPLE_FONT, 0)

        Log.i("DB_VAL", values.toString())

        db.insert(SETTINGS_TABLE_NAME, null, values)
        db.close()
    }
    //////////////////////////////////////////////////////////////////////////////
    // SETTINGS
    fun getFontSize(): Int? {
        val cursor = getSettingData()
        var test = 0
        if (cursor != null) {
            while (cursor.moveToNext()) {
                test = cursor.getInt(cursor.getColumnIndex(ENABLE_LARGE_FONT))
            }
        }
        return test
    }
    fun getSimpleFontSetting(): Int? {
        val cursor = getSettingData()
        var test = 0
        if (cursor != null) {
            while (cursor.moveToNext()) {
                test = cursor.getInt(cursor.getColumnIndex(ENABLE_SIMPLE_FONT))
            }
        }

        return test
    }


    // update size setting
    fun setFontSize(bigMode: Int){
        val db = this.readableDatabase
        db.execSQL("UPDATE $SETTINGS_TABLE_NAME SET $ENABLE_LARGE_FONT = $bigMode WHERE ID=1")
    }

    fun setSimpleFontSetting(simpleMode: Int){
        val db = this.readableDatabase
        db.execSQL("UPDATE $SETTINGS_TABLE_NAME SET $ENABLE_SIMPLE_FONT = $simpleMode WHERE ID=1")
    }

    companion object{
        private val DATABASE_NAME = "StoryGame"
        private val DATABASE_VERSION = 1

        // progression
        val PROGRESSION_TABLE_NAME = "user_progress"
        val ID = "id"
        val STORY_POS = "story_position"
        val COLLECT_FOUND = "collectibles_found"
        val COLLECT_NEEDED = "collectibles_not_found"

        // settings
        val SETTINGS_TABLE_NAME = "user_settings"
        val SETTINGS_ID = "id"
        val ENABLE_LARGE_FONT = "enable_large_font"
        val ENABLE_SIMPLE_FONT = "enable_simple_font"
    }
}