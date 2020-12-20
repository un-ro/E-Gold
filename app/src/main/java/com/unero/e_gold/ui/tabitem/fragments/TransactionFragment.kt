package com.unero.e_gold.ui.tabitem.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.unero.e_gold.R
import com.unero.e_gold.data.model.Transact
import com.unero.e_gold.data.viewmodel.TransactViewModel
import com.unero.e_gold.databinding.FragmentTransactionBinding
import com.unero.e_gold.ui.tabitem.adapters.TransactionAdapter
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class TransactionFragment : Fragment() {

    private lateinit var binding: FragmentTransactionBinding
    private lateinit var mViewModel: TransactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(TransactViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_transaction, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.listTransact.observe(viewLifecycleOwner,  {
            if(it.isEmpty()){
                binding.noItem.visibility = View.VISIBLE
            } else {
                binding.noItem.visibility = View.GONE
                binding.rvTransaction.visibility = View.VISIBLE
                setup(it)
            }
        })

        binding.btnBuy.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_buyFragment)
        }
    }

    private fun setup(d: List<Transact>){
        val listTransact = ArrayList<Transact>()
        for (s in d){
            listTransact.add(s)
        }
        // Recycler
        binding.rvTransaction.layoutManager = LinearLayoutManager(context)
        binding.rvTransaction.adapter = TransactionAdapter(R.layout.item_transaction, listTransact)
    }
}