package com.example.vynils.repository

import android.content.Context
import com.example.vynils.dto.PerformerPrizeDTO
import com.example.vynils.dto.ResponsePrizeDTO
import com.example.vynils.model.Prize
import com.example.vynils.network.NetworkServiceAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.lang.reflect.Type

class PrizeRepository {
    private val gson = Gson()

    private fun responsePostPrize(responsePrize: ResponsePrizeDTO): Prize {
        val performerPrizeDTO = responsePrize.performerPrize.firstOrNull() ?: PerformerPrizeDTO(-1, "Unknown")
        return Prize(
            id = responsePrize.id,
            name = responsePrize.name,
            description = responsePrize.description,
            organization = responsePrize.organization,
            performerPrize = PerformerPrizeDTO(
                id = performerPrizeDTO.id,
                premiationDate = performerPrizeDTO.premiationDate
            )
        )
    }

    suspend fun postPrize(
        context: Context, prize: JSONObject
    ): Int = withContext(Dispatchers.IO) {
        val apiService = NetworkServiceAdapter(context)

        val responseListener = apiService.postPrize(prize)

        val prizeType: Type = object : TypeToken<ResponsePrizeDTO>() {}.type
        val responsePrize: ResponsePrizeDTO = gson.fromJson(responseListener, prizeType)
        responsePrize.id
    }
}