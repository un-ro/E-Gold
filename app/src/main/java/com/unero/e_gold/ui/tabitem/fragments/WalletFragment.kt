package com.unero.e_gold.ui.tabitem.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.unero.e_gold.R
import com.unero.e_gold.data.model.Price
import com.unero.e_gold.data.repository.ApiRepository
import com.unero.e_gold.data.viewmodel.ApiFactory
import com.unero.e_gold.data.viewmodel.ApiViewModel
import com.unero.e_gold.data.viewmodel.TransactViewModel
import com.unero.e_gold.databinding.FragmentWalletBinding
import com.unero.e_gold.ui.tabitem.adapters.PriceAdapter
import com.unero.e_gold.ui.tabitem.adapters.WalletAdapter
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class WalletFragment : Fragment() {

    private lateinit var binding: FragmentWalletBinding
    private lateinit var transactVM: TransactViewModel
    private lateinit var apiVM: ApiViewModel
    private var maxGold = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Transact View Model
        transactVM = ViewModelProvider(this).get(TransactViewModel::class.java)

        // Api View Model
        val factory = ApiFactory(ApiRepository())
        apiVM = ViewModelProvider(this,factory).get(ApiViewModel::class.java)
        apiVM.getPrice()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_wallet, container, false)
        binding.lifecycleOwner = this
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transactVM.totalWeight.observe(viewLifecycleOwner){
            if (it != null)
                maxGold = it

            val word = if (it == null)
                "Total Weight: 0g"
            else
                "Total Weight: ${it}g"
            binding.totalWeight.text = word
        }

        transactVM.totalBuy.observe(viewLifecycleOwner){
            val word = if (it == null)
                "Total Buy: Rp 0"
            else
                "Total Buy: Rp $it"
            binding.totalBuy.text = word
        }

        apiVM.responses.observe(viewLifecycleOwner, {
            setup(it.data.buy_price, it.data.sell_price)
        })
    }

    private fun setup(buy: Int, sell: Int){
        val listPrice = ArrayList<Price>()
        val prices = arrayOf(0.5f, 1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 10.0f, 25.0f, 50.0f, 100.0f)
        Toasty.info(requireContext(), "Maximal $maxGold", Toast.LENGTH_LONG).show()
        if (maxGold != 0.0F){
            for (price in prices)
                if (price <= maxGold)
                    listPrice.add(Price(price, (price*buy).toInt(), (price*sell).toInt()))
            // Recycler
            val adapter = WalletAdapter(R.layout.item_wallet, listPrice)
            binding.rvUserPrice.visibility = View.VISIBLE
            binding.rvUserPrice.layoutManager = GridLayoutManager(context, 2)
            binding.rvUserPrice.adapter = adapter
        } else {
            binding.msgZero.visibility = View.VISIBLE

        }
    }
}