package com.inaki.tmobilecodechallenge.Util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
fun dateFormat(dateJoined: String): String {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val formatter = SimpleDateFormat("MM.dd.yyyy")
    return formatter.format(parser.parse(dateJoined))
}