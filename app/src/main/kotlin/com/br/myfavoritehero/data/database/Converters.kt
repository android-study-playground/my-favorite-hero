package com.br.myfavoritehero.data.database

import androidx.room.TypeConverter
import com.br.myfavoritehero.data.models.Items
import com.br.myfavoritehero.data.models.Thumbnail
import com.br.myfavoritehero.data.models.Url
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable

class Converters: Serializable{

    @TypeConverter
    fun fromString(value: String): ArrayList<String> {
        val listType = object : TypeToken<ArrayList<String>>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromUrlList(optionValues: ArrayList<Url>?): String? {
        if (optionValues == null) { return null }
        val gson = Gson()
        val type = object : TypeToken<ArrayList<Url>>() {}.type
        return gson.toJson(optionValues, type)
    }

    @TypeConverter
    fun toUrlList(optionValuesString: String?): ArrayList<Url>? {
        if (optionValuesString == null) { return null }
        val gson = Gson()
        val type = object : TypeToken<ArrayList<Url>>() {}.type
        return gson.fromJson(optionValuesString, type)
    }

    @TypeConverter
    fun fromItems(optionValues: Items?): String? {
        if (optionValues == null) { return null }
        val gson = Gson()
        val type = object : TypeToken<Items>() {}.type
        return gson.toJson(optionValues, type)
    }

    @TypeConverter
    fun toItems(optionValuesString: String?): Items? {
        if (optionValuesString == null) { return null }
        val gson = Gson()
        val type = object : TypeToken<Items>() {}.type
        return gson.fromJson(optionValuesString, type)
    }

    @TypeConverter
    fun fromThumbnail(optionValues: Thumbnail?): String? {
        if (optionValues == null) { return null }
        val gson = Gson()
        val type = object : TypeToken<Thumbnail>() {}.type
        return gson.toJson(optionValues, type)
    }

    @TypeConverter
    fun toThumbnail(optionValuesString: String?): Thumbnail? {
        if (optionValuesString == null) { return null }
        val gson = Gson()
        val type = object : TypeToken<Thumbnail>() {}.type
        return gson.fromJson(optionValuesString, type)
    }

}
