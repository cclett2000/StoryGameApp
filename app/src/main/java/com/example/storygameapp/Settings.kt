package com.example.storygameapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.storygameapp.db.dbHelper
import com.google.android.material.switchmaterial.SwitchMaterial

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_screen)

        val db = dbHelper(this, null)
        val fontSizeSwitch = findViewById<SwitchMaterial>(R.id.large_font_switch)

        if (db.getFontSize() == 1){
            fontSizeSwitch.isChecked = true
        }

        fontSizeSwitch.setOnClickListener {
            if (fontSizeSwitch.isChecked){
                db.setFontSize(1)
                Log.i("FONT_SIZE_UPDATE", "Font Size is now big!")
            }
            else{
                db.setFontSize(0)
                Log.i("FONT_SIZE_UPDATE", "Font Size is now smallish!")
            }
        }
    }
}