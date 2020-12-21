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
import com.unero.e_gold.data.model.Transact
import com.unero.e_gold.data.repository.ApiRepository
import com.unero.e_gold.data.viewmodel.ApiFactory
import com.unero.e_gold.data.viewmodel.ApiViewModel
import com.unero.e_gold.data.viewmodel.TransactViewModel
import com.unero.e_gold.databinding.FragmentPriceBinding
import com.unero.e_gold.ui.tabitem.adapters.PriceAdapter
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.InternalCoroutinesApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@InternalCoroutinesApi
class PriceFragment : Fragment() {

    private lateinit var apiVM: ApiViewModel
    private lateinit var transactVM: TransactViewModel
    private lateinit var binding: FragmentPriceBinding

    private lateinit var today: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transactVM = ViewModelProvider(this).get(TransactViewModel::class.java)

        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        today = current.format(formatter)

        val factory = ApiFactory(ApiRepository())
        apiVM = ViewModelProvider(this,factory).get(ApiViewModel::class.java)
        apiVM.getPrice()
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
        apiVM.responses.observe(viewLifecycleOwner, {
            binding.dateGet.text = "Fetch at\n ${it.data.date}"
            setup(it.data.buy_price, it.data.sell_price)
        })
    }

    private fun setup(buy: Int, sell: Int){
        val listPrice = ArrayList<Price>()
        val prices = arrayOf(0.5f, 1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 10.0f, 25.0f, 50.0f, 100.0f)
        for (price in prices)
            listPrice.add(Price(price, (price*buy).toInt(), (price*sell).toInt()))
        setupRecycler(listPrice)
    }

    private fun setupRecycler(list: ArrayList<Price>){
        val adapter = PriceAdapter(R.layout.item_price, list)
        // RecyclerView
        binding.rvPrice.layoutManager = LinearLayoutManager(context)
        binding.rvPrice.adapter = PriceAdapter(R.layout.item_price, list)
        binding.rvPrice.adapter = adapter
        // BRVAH
        adapter.setOnItemClickListener { adapter, view, position ->
            val data: Price = adapter.data[position] as Price
            when (data.weight == 0.5F){
                true -> {
                    transactVM.add(Transact(today, data.weight, (data.buy*2)))
                    Toasty.success(requireContext(), "Add Transaction Sucess", Toast.LENGTH_SHORT).show()
                    }
                false -> {
                    transactVM.add(Transact(today, data.weight, (data.buy /(data.weight).toInt())))
                    Toasty.success(requireContext(), "Add Transaction Sucess", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}