package com.example.storygameapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //button vars
        val settings_btn = findViewById<ImageButton>(R.id.settings_BTN)
        val continue_btn = findViewById<Button>(R.id.continue_game_BTN)
        val new_game_btn = findViewById<Button>(R.id.new_game_BTN)
        val collectible_btn = findViewById<Button>(R.id.collectible_BTN)
        val credits_btn = findViewById<Button>(R.id.credits_BTN)

        // settings logic
        settings_btn.setOnClickListener{
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
        }

        // continue game logic
        continue_btn.setOnClickListener{
            val intent = Intent(this, ContinueGame::class.java)
            startActivity(intent)
        }

        // new game logic
        new_game_btn.setOnClickListener{
            val intent = Intent(this, NewGame::class.java)
            startActivity(intent)
        }

        // collectiblle logic
        collectible_btn.setOnClickListener{
            val intent = Intent(this, Collectibles::class.java)
            startActivity(intent)
        }

        // credits logic
        credits_btn.setOnClickListener{
            val intent = Intent(this, Credits::class.java)
            startActivity(intent)
        }
    }
}