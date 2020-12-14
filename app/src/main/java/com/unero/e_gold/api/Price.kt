package com.unero.e_gold.api

data class Outer<T>(
    val data: T,
    val code: String,
    val latency: String
)

data class Price(
    val price_id: String,
    val sell_price: Int,
    val buy_price: Int,
    val date: String,
    val partner_sell_price: Int,
    val tokopedia_sell_percentage: Float,
    val is_future: Int
)