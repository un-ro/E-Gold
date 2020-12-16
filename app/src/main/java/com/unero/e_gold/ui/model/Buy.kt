package com.unero.e_gold.ui.model

import com.github.arisan.annotation.Form

class Buy(){
    @Form(position = 1)
    private var price: Int = 0

    @Form(type = Form.SPINNER, position = 2)
    private var weight: Float = 0f

    @Form(label = "Buy from ",type = Form.DATE,position = 3,format = "dd-MMM-yyyy")
    private var date: String = ""

    val listWeight = arrayOf(0.5f, 1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 10.0f, 25.0f, 50.0f, 100.0f)
}