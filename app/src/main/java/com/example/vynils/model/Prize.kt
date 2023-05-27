package com.example.vynils.model
import android.os.Parcelable
import com.example.vynils.dto.PerformerPrizeDTO
import kotlinx.parcelize.Parcelize

@Parcelize
data class Prize(
    val id: Int,
    val name: String,
    val organization: String?,
    val description: String?,
    val performerPrize: PerformerPrizeDTO? = null
) : Parcelable