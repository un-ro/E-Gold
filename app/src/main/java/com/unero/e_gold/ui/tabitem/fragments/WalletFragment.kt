package com.unero.e_gold.ui.tabitem.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.unero.e_gold.R
import com.unero.e_gold.databinding.FragmentWalletBinding
import com.unero.e_gold.ui.viewmodel.TransactViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class WalletFragment : Fragment() {

    private lateinit var binding: FragmentWalletBinding
    private lateinit var mViewModel: TransactViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_wallet, container, false)
        mViewModel = ViewModelProvider(this).get(TransactViewModel::class.java)
        binding.lifecycleOwner = this
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.totalWeight.observe(viewLifecycleOwner){
            binding.totalWeight.text = "$it g"
        }

        mViewModel.totalBuy.observe(viewLifecycleOwner){
            binding.totalBuy.text = "Rp $it"
        }
    }
}