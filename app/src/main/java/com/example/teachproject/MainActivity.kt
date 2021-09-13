package com.example.teachproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonMusic = findViewById<Button>(R.id.button_music)
        val buttonMap = findViewById<Button>(R.id.button_share)

        buttonMusic.setOnClickListener(this)
        buttonMap.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.button_music -> {
                val intent = Intent(Intent.ACTION_MAIN)
                intent.addCategory(Intent.CATEGORY_APP_MUSIC)
                startActivity(intent)
            }
            R.id.button_share -> {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_EMAIL, "test@gmail.com")
                intent.putExtra(Intent.EXTRA_TEXT, "How are you?")
                startActivity(intent)
            }
        }
    }
}