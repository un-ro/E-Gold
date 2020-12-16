package com.unero.e_gold.ui.tabitem.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.unero.e_gold.R
import com.unero.e_gold.data.model.Transact
import com.unero.e_gold.databinding.FragmentTransactionBinding
import com.unero.e_gold.ui.viewmodel.TransactViewModel
import com.unero.e_gold.ui.tabitem.adapter.TransactionAdapter
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class TransactionFragment : Fragment() {

    private val listTransact = ArrayList<Transact>()
    private lateinit var binding: FragmentTransactionBinding
    private lateinit var mViewModel: TransactViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_transaction, container, false)
        mViewModel = ViewModelProvider(this).get(TransactViewModel::class.java)
        binding.lifecycleOwner = this
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.listTransact.observe(viewLifecycleOwner,  {
            plan(it)
        })
        // Recycler
        binding.rvTransaction.layoutManager = LinearLayoutManager(context)
        binding.rvTransaction.adapter = TransactionAdapter(R.layout.item_transaction, listTransact)

        binding.cvBuy.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_buyFragment)
        }
    }

    private fun plan(d: List<Transact>){
        for (s in d){
            listTransact.add(s)
        }
    }
}