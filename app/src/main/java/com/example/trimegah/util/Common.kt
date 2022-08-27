package com.example.trimegah.util

import com.example.trimegah.model.RunningTradeModel
import com.example.trimegah.model.TModel
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class Common() {
    fun toCurrency(targetNumber: Double, withFraction: Boolean): String {
        val bigDecimal = BigDecimal(targetNumber)
        val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
        val symbols = formatter.decimalFormatSymbols
        //symbols.setDecimalSeparator(".".charAt(0));
        symbols.groupingSeparator = ','
        formatter.decimalFormatSymbols = symbols
        if (withFraction) {
            formatter.maximumFractionDigits = 2
            formatter.minimumFractionDigits = 2
            return formatter.format(bigDecimal.toDouble())
        }
        return formatter.format(bigDecimal.toLong())
    }

    fun generateRandom() : RunningTradeModel {
        val feedCode = listOf("PGAS", "ASII", "GOTO", "BBRI", "AALI", "APAR")
        val retval = RunningTradeModel(feedCode[Random.nextInt(0,feedCode.size-1)],
            Date().time,
            Random.nextInt(1000, 4000),
            Random.nextInt(0, 100),
            Random.nextInt(500, 2000)
        )
        return retval
    }

    fun generateRandomT() : TModel {
        val feedCode = listOf("PGAS", "ASII", "GOTO", "BBRI", "AALI", "APAR")
        val retval = TModel(feedCode[Random.nextInt(0,feedCode.size-1)],
            Date().time,
            Random.nextInt(1000, 4000),
            Random.nextInt(-100, 100),
            Random.nextInt(500, 2000)
        )
        return retval
    }

    fun formatThousand(str: Int) : String {
        return toCurrency(str.toDouble(), false)
    }

    fun plusSign(str: Int) : String{
        val x = str.toDouble()
        if(x < 0) return "$str"
        return "+$str"
    }


    fun formatDate(inputDate: Long) : String{
        val formatter = SimpleDateFormat("HH:mm:ss", Locale.US)
        val date = Date(inputDate)
        return formatter.format(date)
    }
}