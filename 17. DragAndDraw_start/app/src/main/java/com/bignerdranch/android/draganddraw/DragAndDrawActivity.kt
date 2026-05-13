package com.bignerdranch.android.draganddraw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat

class DragAndDrawActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge display on API level < 35
        WindowCompat.enableEdgeToEdge(window)

        // Display the layout
        setContentView(R.layout.activity_drag_and_draw)

        // Ensure that system bars remain visible regardless of the background color:
        // done via XML styling
    }
}
