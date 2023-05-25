package com.example.vynils.ui.album

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.vynils.dto.CreateAlbumDTO
import com.example.vynils.MainActivity
import com.example.vynils.R
import com.example.vynils.genre.Genre
import com.example.vynils.recordlabel.RecordLabel

class CreateAlbumActivity : AppCompatActivity() {
    private val viewModel: CreateAlbumViewModel by viewModels()
    private var isAllFieldsChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_album)

        val toolbar: Toolbar = findViewById(R.id.album_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Create Album"

        val submitButton: Button = findViewById(R.id.submit_button)
        val genres = mutableListOf<String>()
        for (genre in Genre.values()){
            genres.add(genre.displayName)
        }
        val records = mutableListOf<String>()
        for (record in RecordLabel.values()){
            records.add(record.displayName)
        }
        var genreSelected = ""
        var recordLabelSelected = ""
        val autoCompleteGenre : AutoCompleteTextView = findViewById(R.id.auto_complete_genre)
        val autoCompleteRecord : AutoCompleteTextView = findViewById(R.id.auto_complete_record)
        val adapterGenre = ArrayAdapter(this, R.layout.list_items,genres)
        val adapterRecord = ArrayAdapter(this, R.layout.list_items,records)

        autoCompleteGenre.setAdapter(adapterGenre)
        autoCompleteGenre.onItemClickListener = AdapterView.OnItemClickListener{
                adapterView, view, i, l ->
            genreSelected = adapterView.getItemAtPosition(i).toString()
        }

        autoCompleteRecord.setAdapter(adapterRecord)
        autoCompleteRecord.onItemClickListener = AdapterView.OnItemClickListener{
                adapterView, view, i, l ->
            recordLabelSelected = adapterView.getItemAtPosition(i).toString()
        }

        submitButton.setOnClickListener {
            val etName: EditText = findViewById(R.id.name_field)
            val name = etName.text.toString()
            val etCover: EditText =  findViewById(R.id.cover_field)
            val cover = etCover.text.toString()
            val etReleaseDate : EditText =  findViewById(R.id.release_date_field)
            val releaseDate = etReleaseDate.text.toString()
            val etDescription : EditText =  findViewById(R.id.description_field)
            val description = etDescription.text.toString()
            val genre = genreSelected
            val recordLabel = recordLabelSelected

            isAllFieldsChecked = checkAllFields(etName, etCover, etReleaseDate, etDescription, genre, recordLabel)

            if(isAllFieldsChecked){
                val album = CreateAlbumDTO(name, cover, releaseDate, description, genre, recordLabel)
                viewModel.createAlbum(album)

                val intent= Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun checkAllFields(
        etName: EditText,
        etCover: EditText,
        etReleaseDate: EditText,
        etDescription: EditText,
        genre: String,
        recordLabel: String
    ): Boolean {
        if (etName.length() == 0) {
            etName.error = "This field is required"
            return false
        }
        if (etCover.length() == 0) {
            etCover.error = "This field is required"
            return false
        }
        if (etReleaseDate.length() == 0) {
            etReleaseDate.error = "Email is required"
            return false
        }
        if (etDescription.length() == 0) {
            etDescription.error = "Password is required"
            return false
        }
        if (genre.isEmpty()){
            return false
        }
        if (recordLabel.isEmpty()){
            return false
        }
        // after all validation return true.
        return true
    }
}
