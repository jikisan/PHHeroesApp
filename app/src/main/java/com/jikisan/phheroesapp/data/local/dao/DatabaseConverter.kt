package com.jikisan.phheroesapp.data.local.dao

import androidx.room.TypeConverter
import androidx.room.util.newStringBuilder

class DatabaseConverter {

    private val separator = ","

    @TypeConverter
    fun convertListToString(list: List<String>): String{
        val stringBuilder = newStringBuilder()
        for (item in list){
            stringBuilder.append(item).append(separator)
        }

        stringBuilder.setLength(stringBuilder.length - separator.length)
        return stringBuilder.toString()
    }

    @TypeConverter
    fun convertStringToList(string: String): List<String>{
        return  string.split(separator)
    }
}