package com.unero.e_gold.ui.tabitem.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.unero.e_gold.R
import com.unero.e_gold.data.model.Price
import com.unero.e_gold.data.repository.ApiRepository
import com.unero.e_gold.data.viewmodel.ApiFactory
import com.unero.e_gold.data.viewmodel.ApiViewModel
import com.unero.e_gold.databinding.FragmentPriceBinding
import com.unero.e_gold.ui.tabitem.adapters.PriceAdapter
import com.unero.e_gold.ui.tabitem.adapters.TransactionAdapter
import es.dmoral.toasty.Toasty

class PriceFragment : Fragment() {

    private lateinit var mViewModel: ApiViewModel
    private lateinit var binding: FragmentPriceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = ApiFactory(ApiRepository())
        mViewModel = ViewModelProvider(this,factory).get(ApiViewModel::class.java)
        mViewModel.getPrice()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_price, container, false)
        binding.lifecycleOwner = this
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.responses.observe(viewLifecycleOwner, {
            binding.text.text = it.data.date
            setup(it.data.buy_price, it.data.sell_price)
        })
    }

    private fun setup(buy: Int, sell: Int){
        val listPrice = ArrayList<Price>()
        val prices = arrayOf(0.5f, 1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 10.0f, 25.0f, 50.0f, 100.0f)
        for (price in prices)
            listPrice.add(Price(price, (price*buy).toInt(), (price*sell).toInt()))
        // Recycler
        val adapter = PriceAdapter(R.layout.item_price, listPrice)
        binding.rvPrice.layoutManager = LinearLayoutManager(context)
        binding.rvPrice.adapter = adapter
        Toasty.info(requireContext(), "SETUP SELESAI", Toast.LENGTH_LONG).show()
    }
}