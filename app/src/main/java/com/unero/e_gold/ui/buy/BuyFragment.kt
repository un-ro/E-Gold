package com.unero.e_gold.ui.buy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.unero.e_gold.R
import com.unero.e_gold.data.model.Transact
import com.unero.e_gold.data.viewmodel.TransactViewModel
import com.unero.e_gold.databinding.FragmentBuyBinding
import com.unero.e_gold.ui.Buy
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class BuyFragment : Fragment() {

    private lateinit var binding: FragmentBuyBinding
    private lateinit var mViewModel: TransactViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_buy, container, false)
        mViewModel = ViewModelProvider(this).get(TransactViewModel::class.java)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        form()
    }

    private fun form() {
        var weight = 0.0F
        var price = 0
        var date = ""

        binding.arisanForm.setModels(Buy())
        binding.arisanForm.fillData("weight", Buy().listWeight)
        // Listener
        binding.arisanForm.addListener("price") { value, _ ->
            Toast.makeText(requireContext(), value, Toast.LENGTH_SHORT).show()
            price = value.toInt()
        }
        binding.arisanForm.addListener("weight") { value, _ ->
            Toast.makeText(requireContext(), value, Toast.LENGTH_SHORT).show()
            weight = value.toFloat()
        }
        binding.arisanForm.addListener("date") { value, _ ->
            Toast.makeText(requireContext(), value, Toast.LENGTH_SHORT).show()
            date = value.toString()
        }

        binding.arisanForm.setOnSubmitListener {
            mViewModel.add(Transact(date,weight,price))
            Toasty.info(requireContext(), "Transaction Completed", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_buyFragment_to_homeFragment)
        }
        binding.arisanForm.buildForm()
    }
}