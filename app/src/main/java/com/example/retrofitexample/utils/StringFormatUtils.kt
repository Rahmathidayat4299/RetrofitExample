package com.example.retrofitexample.utils

import android.icu.text.NumberFormat
import android.icu.util.Currency
import java.util.Locale
import kotlin.math.roundToInt

fun Double.toCurrencyFormat(): String {
    val locale = Locale("us", "US") //
    val format: NumberFormat = NumberFormat.getCurrencyInstance(locale)
    format.maximumFractionDigits = 0
    format.currency = Currency.getInstance("IDR")
    return format.format(this.roundToInt()).replace("IDR", "$")
}