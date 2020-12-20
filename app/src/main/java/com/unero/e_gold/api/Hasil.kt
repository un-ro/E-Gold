package com.unero.e_gold.api

data class Hasil(
    var data: Price
)

data class Price(
    var sell_price: Int,
    var buy_price: Int,
    var date: String
)