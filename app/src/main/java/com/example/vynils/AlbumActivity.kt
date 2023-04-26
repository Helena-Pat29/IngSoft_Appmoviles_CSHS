package com.example.vynils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import com.example.vynils.brokers.VolleyBroker

class AlbumActivity : AppCompatActivity() {
    lateinit var volleyBroker: VolleyBroker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        volleyBroker = VolleyBroker(this.applicationContext)

        val getResultAlbumTextView : TextView = findViewById(R.id.get_result_text_album)
        volleyBroker.instance.add(VolleyBroker.getRequest("albums",
            { response ->
                // Display the first 500 characters of the response string.
                getResultAlbumTextView.text = "Response is: ${response}"
            },
            {
                Log.d("TAG", it.toString())
                getResultAlbumTextView.text = "That didn't work!"
            }
        ))

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.layout_menu, menu)
        supportActionBar!!.title = "Albumes"
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home -> {
                // Create an intent with a destination of the other Activity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}