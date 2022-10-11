package com.example.storygameapp

import android.graphics.Typeface
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.example.storygameapp.db.dbHelper

class GameScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_screen)

        val db = dbHelper(this, null)
        val storyView = findViewById<TextView>(R.id.story_body_TV)

        storyView.movementMethod = ScrollingMovementMethod()

        // font sizing
        if (db.getFontSize() == 0){
            storyView.textSize = 24F
        }
        else{
            storyView.textSize = 30F
        }

        // font styling
//        if (db.getSimpleFontSetting() == 0){
//            val font = Typeface.createFromAsset(assets, "cursive.ttf")
//            storyView.typeface = font
//        }
//        else{
//            val font = Typeface.createFromAsset(assets, "serif.ttf")
//            storyView.typeface = font
//        }
    }
}