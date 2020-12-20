package com.unero.e_gold.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.unero.e_gold.R
import com.unero.e_gold.data.viewmodel.AccountViewModel
import com.unero.e_gold.databinding.FragmentSplashBinding
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class SplashFragment : Fragment() {

    private lateinit var mViewModel: AccountViewModel
    private lateinit var binding: FragmentSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        binding.lifecycleOwner = this
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mViewModel.anyAccount.observe(viewLifecycleOwner, {
            Handler(Looper.getMainLooper()).postDelayed({
                // Navigation
                if (it.isNullOrEmpty()){
                    Toasty.info(requireContext(), "Create your data first", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                } else {
                    Toasty.normal(requireContext(), "Welcome ${it[0].username}", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                }
            }, 1000)
        })
    }
}