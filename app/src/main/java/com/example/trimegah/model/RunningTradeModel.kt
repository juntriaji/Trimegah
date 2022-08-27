package com.example.trimegah.model

import com.example.trimegah.util.Common

data class RunningTradeModel (
    val feedCode: String,
    val time: Long,
    val price: Int,
    val change: Int,
    val vol: Int,
)
