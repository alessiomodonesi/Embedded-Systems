package com.example.recyclersample

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat

class DetailActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge display on API level < 35
        WindowCompat.enableEdgeToEdge(window)

        // Display the layout
        setContentView(R.layout.activity_detail)

        // recupero il riferimento alla TextView
        val textView = findViewById<TextView>(R.id.flower_info)

        // catturo il nome del fiore dall'intent e lo stesso come text
        textView.text = "${intent.getStringExtra("FLOWER_NAME")} is a nice flower"
    }
}