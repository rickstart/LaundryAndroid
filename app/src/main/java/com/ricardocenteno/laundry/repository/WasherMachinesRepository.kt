package com.ricardocenteno.laundry.repository

import android.content.Context
import com.ricardocenteno.laundry.models.Washer
import org.json.JSONObject
import java.io.IOException

class WasherMachinesRepository( private val context: Context) {

    fun getMachines(): MutableList<Washer> {
        return populateList()
    }

    private fun populateList(): MutableList<Washer> {
        val washers = mutableListOf<Washer>()
        val json = loadJSONFromAsset(context)
        json?.let {
            val jsonObject = JSONObject(it)
            for (i in 0 until jsonObject.getJSONArray("devices").length()) {
                val jsonWasher = jsonObject.getJSONArray("devices").getJSONObject(i)

                washers.add(Washer(
                    jsonWasher.getInt("id"),
                    jsonWasher.getString("label"),
                    Washer.Status(jsonWasher.getString("status"))
                ))
            }
        }

        return washers
    }

    private fun loadJSONFromAsset(context: Context): String? {
        var json: String? = null
        try {
            val input = context.getAssets().open("devices_feed.json")
            val size = input.available()
            val buffer = ByteArray(size)
            input.read(buffer)
            input.close()
            json = String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }

        return json

    }
}