package com.example.vynils.ui.prize

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import com.example.vynils.R
import org.json.JSONObject

class PrizeActivity : AppCompatActivity() {
    private val viewModel: PrizeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prize)

        val postButton: Button = findViewById(R.id.post_prize_button)
        postButton.setOnClickListener {
            val name = "Premio de prueba"
            val description = "Descripcion del premio de prueba"
            val organization = "Organizacion con todo animo de lucro"
            val postParams = mapOf<String, Any>(
                "name" to name,
                "description" to description,
                "organization" to organization
            )

            viewModel.postPrize(JSONObject(postParams))


        }

    }
}