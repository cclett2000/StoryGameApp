package com.example.storygameapp.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

@SuppressLint("Range")
class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
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
                    ENABLE_SIMPLE_FONT + " INTEGER," +
                    SETTINGS_DB_INIT + " INTEGER" +
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

    fun initSettingTable(){
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(ENABLE_LARGE_FONT, 0)
        values.put(ENABLE_SIMPLE_FONT, 0)
        values.put(SETTINGS_DB_INIT, 1)

        Log.i("DB_VAL", values.toString())

        db.insert(SETTINGS_TABLE_NAME, null, values)
        db.close()
    }

    // TODO: Implement this
    fun initProgressionTable(){
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(ENABLE_LARGE_FONT, 0)
        values.put(ENABLE_SIMPLE_FONT, 0)
        values.put(PROGRESSION_DB_INIT, 1)

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
    fun getSettingDBStatus(): Int?{
        val cursor = getSettingData()
        var test = 0
        if (cursor != null) {
            while (cursor.moveToNext()) {
                test = cursor.getInt(cursor.getColumnIndex(SETTINGS_DB_INIT))
            }
        }
        return test
    }

    fun setFontSize(bigMode: Int){
        val db = this.readableDatabase
        db.execSQL("UPDATE $SETTINGS_TABLE_NAME SET $ENABLE_LARGE_FONT = $bigMode WHERE ID=1")
    }
    fun setSimpleFontSetting(simpleMode: Int){
        val db = this.readableDatabase
        db.execSQL("UPDATE $SETTINGS_TABLE_NAME SET $ENABLE_SIMPLE_FONT = $simpleMode WHERE ID=1")
    }
    fun setSettingInitStatus(simpleMode: Int){
        val db = this.readableDatabase
        db.execSQL("UPDATE $SETTINGS_TABLE_NAME SETS$SETTINGS_DB_INIT = $simpleMode WHERE ID=1")
    }

    // PROGRESSION
    fun getCollectiblesFound(): Int? {
        val cursor = getProgressionData()
        var test = 0
        if (cursor != null) {
            while (cursor.moveToNext()) {
                test = cursor.getInt(cursor.getColumnIndex(COLLECT_FOUND))
            }
        }
        return test
    }
    fun getCollectiblesNeeded(): Int? {
        val cursor = getProgressionData()
        var test = 0
        if (cursor != null) {
            while (cursor.moveToNext()) {
                test = cursor.getInt(cursor.getColumnIndex(COLLECT_NEEDED))
            }
        }
        return test
    }
    fun getStoryProgress(): Int? {
        val cursor = getProgressionData()
        var test = 0
        if (cursor != null) {
            while (cursor.moveToNext()) {
                test = cursor.getInt(cursor.getColumnIndex(STORY_POS))
            }
        }

        return test
    }
    fun getProgressionDBStatus(): Int?{
        val cursor = getSettingData()
        var test = 0
        if (cursor != null) {
            while (cursor.moveToNext()) {
                test = cursor.getInt(cursor.getColumnIndex(SETTINGS_DB_INIT))
            }
        }
        return test
    }

    fun setStoryPos(bigMode: Int){
        val db = this.readableDatabase
        db.execSQL("UPDATE $PROGRESSION_TABLE_NAME SET $STORY_POS = $bigMode WHERE ID=1")
    }
    fun setCollectFound(bigMode: Int){
        val db = this.readableDatabase
        db.execSQL("UPDATE $PROGRESSION_TABLE_NAME SET $COLLECT_FOUND = $bigMode WHERE ID=1")
    }
    fun setCollectNeeded(simpleMode: Int){
        val db = this.readableDatabase
        db.execSQL("PROGRES$PROGRESSION_TABLE_NAME SET $COLLECT_NEEDED = $simpleMode WHERE ID=1")
    }
    fun setProgressionInitStatus(simpleMode: Int){
        val db = this.readableDatabase
        db.execSQL("PROGRES$PROGRESSION_TABLE_NAME SETS$PROGRESSION_DB_INIT = $simpleMode WHERE ID=1")
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
        val PROGRESSION_DB_INIT = "progression_db_init"

        // settings
        val SETTINGS_TABLE_NAME = "user_settings"
        val SETTINGS_ID = "id"
        val ENABLE_LARGE_FONT = "enable_large_font"
        val ENABLE_SIMPLE_FONT = "enable_simple_font"
        val SETTINGS_DB_INIT = "setting_db_init"
    }
}