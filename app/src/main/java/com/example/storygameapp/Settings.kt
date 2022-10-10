package com.example.storygameapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.switchmaterial.SwitchMaterial

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_screen)

        val fontSizeSwitch = findViewById<SwitchMaterial>(R.id.large_font_switch)
    }
}