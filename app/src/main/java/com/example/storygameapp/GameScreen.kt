package com.example.storygameapp

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_screen)

        val story_view = findViewById<TextView>(R.id.story_body_TV)
        story_view.movementMethod = ScrollingMovementMethod()
    }
}