package com.view

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.bledemo.R

class ViewHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_home_activity)
        val childLayout = findViewById<LinearLayout>(R.id.child_layout)
        childLayout.post {
            Log.i("ViewHomeActivity", "top :${childLayout.top}  ")
        }
    }
}