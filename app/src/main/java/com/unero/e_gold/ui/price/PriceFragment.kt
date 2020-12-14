package com.unero.e_gold.ui.price

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.unero.e_gold.R
import com.unero.e_gold.databinding.FragmentPriceBinding
import com.unero.e_gold.models.Price

class PriceFragment : Fragment() {

    val listPrice = ArrayList<Price>()
    lateinit var binding: FragmentPriceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_price, container, false)
        setup()
        binding.rvPrice.layoutManager = LinearLayoutManager(context)
        binding.rvPrice.adapter = PriceAdapter(R.layout.item_price, listPrice)
        // Inflate the layout for this fragment
        return binding.root
    }

    fun setup(){
        val dd = listOf(0.5f, 1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 10.0f, 25.0f, 50.0f, 100.0f)
        val b = 899000
        val s = 858920
        for (d in dd){
            listPrice.add(Price(d, "Rp " + (d*s), "Rp " + (d*b)))
        }
    }
}