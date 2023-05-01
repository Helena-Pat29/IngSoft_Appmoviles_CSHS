package com.example.vynils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutResId = intent.getIntExtra("layoutResId", R.layout.album_item)
        setContentView(layoutResId)
    }
}