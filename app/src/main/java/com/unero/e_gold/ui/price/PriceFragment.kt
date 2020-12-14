package com.unero.e_gold.ui.price

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.unero.e_gold.R
import com.unero.e_gold.databinding.FragmentPriceBinding
import com.unero.e_gold.models.Price

class PriceFragment : Fragment() {

    private val listPrice = ArrayList<Price>()
    private lateinit var binding: FragmentPriceBinding
    private lateinit var viewModel: PriceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_price, container, false)
        viewModel = ViewModelProvider(this).get(PriceViewModel::class.java)

        viewModel.getPrice().observe(viewLifecycleOwner,{
            if (it != null) {
                setup(it.data.sell_price, it.data.buy_price)
                print("MASUK GAN ${it.data.sell_price}")
            } else {
                print("TOLOL: KOSONG / NULL")
            }
        })
        binding.rvPrice.layoutManager = LinearLayoutManager(context)
        binding.rvPrice.adapter = PriceAdapter(R.layout.item_price, listPrice)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setup(sell: Int, buy: Int){
        val prices = listOf(0.5f, 1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 10.0f, 25.0f, 50.0f, 100.0f)
        for (p in prices){
            listPrice.add(Price(p, "Rp " + (p*sell).toInt(), "Rp " + (p*buy).toInt()))
        }
    }
}