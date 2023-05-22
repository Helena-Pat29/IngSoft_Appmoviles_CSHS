package com.example.vynils.ui.prize

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.vynils.MainActivity
import com.example.vynils.R
import org.json.JSONObject

class PrizeActivity : AppCompatActivity() {
    private val viewModel: PrizeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prize)

        val toolbar: Toolbar = findViewById(R.id.prize_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Crear Premio"

        val postButton: Button = findViewById(R.id.post_prize_button)
        postButton.setOnClickListener {
            val namefield : EditText = findViewById(R.id.post_prize_name)
            val name = namefield.text.toString()
            val descriptionfield : EditText = findViewById(R.id.post_prize_description)
            val description =descriptionfield.text.toString()
            val organizationfield : EditText = findViewById(R.id.post_prize_organization)
            val organization =organizationfield.text.toString()

            val postParams = mapOf<String, Any>(
                "name" to name,
                "description" to description,
                "organization" to organization
            )

            viewModel.postPrize(JSONObject(postParams))

            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)

        }

        val cancelButton: Button =findViewById(R.id.post_prize_button_cancel)
        cancelButton.setOnClickListener{
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
