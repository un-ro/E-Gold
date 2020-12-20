package com.unero.e_gold.ui.tabitem.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.unero.e_gold.R
import com.unero.e_gold.data.viewmodel.TransactViewModel
import com.unero.e_gold.databinding.FragmentWalletBinding
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class WalletFragment : Fragment() {

    private lateinit var binding: FragmentWalletBinding
    private lateinit var mViewModel: TransactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(TransactViewModel::class.java)
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
        mViewModel.totalWeight.observe(viewLifecycleOwner){
            val word = if (it == null)
                "0g"
            else
                "${it}g"
            binding.totalWeight.text = word
        }

        mViewModel.totalBuy.observe(viewLifecycleOwner){
            val word = if (it == null)
                "Rp 0"
            else
                "Rp $it"
            binding.totalBuy.text = word
        }
    }
}