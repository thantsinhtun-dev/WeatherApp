package com.stone.weather.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class AppUtils {
    companion object{
        private fun getCurrentDate(addDay:Long): String {
            val currentDate = LocalDate.now().plusDays(addDay)
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            return formatter.format(currentDate)
        }
        private fun getDateFormat(date:String):String{
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val date = LocalDate.parse(date, formatter)//LocalDate()//LocalDateTime
            return date.toString()
        }
        fun checkDate(date:String,current:Long):Boolean{
            val currentDate= getCurrentDate(current)
            val str= getDateFormat(date)
            return currentDate.equals(str)
        }
        fun getCurrentDay(): String {
            val current = LocalDate.now()
            val formatter = DateTimeFormatter.ofPattern("E, dd MMM")
            return formatter.format(current)
        }

        //convert yyyy-MM-dd HH:mm:ss to E, dd MMM
        fun formatDate(date: String?):String{
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val reformatter = DateTimeFormatter.ofPattern("hh:mm a \ndd MMM")
           // val date:LocalDateTime = LocalDateTime.parse(string, formatter)//LocalDate()//LocalDateTime
//            val reformat= ofPattern("HH:MM E, dd MM")
//            val reDate=LocalDateTime.parse(date,reformat)
           return reformatter.format(formatter.parse(date))
        }
    }
}