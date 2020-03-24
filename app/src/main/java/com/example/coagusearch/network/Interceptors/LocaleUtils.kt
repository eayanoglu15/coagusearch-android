package com.example.coagusearch.network.Interceptors

import android.content.Context
import androidx.core.os.ConfigurationCompat
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

const val ENGLISH_LANGUAGE = "en"

fun Context.getCurrentLocale(): Locale {
    return ConfigurationCompat.getLocales(this.resources.configuration)[0]
}

fun Context.getCurrentLocaleCode(): String {
    val countryCode = getCurrentLocale().country
    val languageCode = getCurrentLocale().language
    return "${languageCode}_$countryCode"
}

fun getCurrentTimezone(): String {
    return TimeZone.getDefault().id
}


fun Double.integerFormat(): String {
    val format = DecimalFormat("#")
    return format.format(this)
}




fun String.integerFormat(): String {
    val format = DecimalFormat("#")
    return format.format(this.toDouble())
}



fun isWhole(value: Double): Boolean {
    return value - value.toInt() == 0.0
}

fun isWhole(value: String): Boolean {
    if (value.toDouble() == 0.0) return true
    return value.toDouble() - Math.floor(value.toDouble()) == 0.0
}

fun Double.kilogramToPound(): Double = (this * 2.20462262185)

fun Double.poundToKilogram(): Double = (this * 0.45359237)

fun Double.inchToCentimeter(): Double = this * 2.54

fun Double.centimeterToInch(): Double = this / 2.54

fun Double.inchToFeetAndInchPair(): Pair<Int, Double> {
    val feetPart: Int = Math.floor(this / 12).toInt()
    val inchesPart = this - (feetPart * 12)

    return Pair(feetPart, inchesPart)
}

fun Double.inchToFeetAndInch(): String {
    val feetPart: Int = Math.floor(this / 12).toInt()
    val inchesPart = Math.ceil(this - (feetPart * 12)).toInt()

    return String.format("%d' %d\"", feetPart, inchesPart)
}


fun Pair<Int, Double>.feetAndInchToInch(): Double {
    var inch = (this.first * 12).toDouble()
    inch += this.second
    return inch
}

fun Locale.isMetric(): Boolean {
    return when (country.toUpperCase()) {
        "US", "LR", "MM" -> false
        else -> true
    }
}

fun Context.isDayMonthDatePattern(): Boolean {
    val dateFormat: DateFormat = android.text.format.DateFormat.getDateFormat(this)
    val pattern = (dateFormat as SimpleDateFormat).toLocalizedPattern()
    return pattern.indexOf("d") < pattern.indexOf("M")
}

fun Context.getDatePattern(): String {
    return if (this.isDayMonthDatePattern()) {
        "gg/aa/yyyy"
    } else {
        "mm/dd/yyyy"
    }
}