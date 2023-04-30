package com.example.vynils.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Performer(
    val id: Int,
    val name: String,
    val image: String?,
    val description: String?,
    val birthDate: String?
)  : Parcelable
