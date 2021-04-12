package com.decagon.anietie.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val value1 = intent.getStringExtra("Active")

        val value2 = intent.getStringExtra("Inactive")

        val textView = findViewById<TextView>(R.id.text)

        textView.text = if (value1 == null) {
            value2
        } else {
         value1
        }
    }
}