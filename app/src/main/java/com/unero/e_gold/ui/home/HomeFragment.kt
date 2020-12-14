package com.unero.e_gold.ui.home

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.google.android.material.tabs.TabLayoutMediator
import com.unero.e_gold.R
import com.unero.e_gold.databinding.FragmentHomeBinding
import com.unero.e_gold.models.Profile
import kotlinx.coroutines.InternalCoroutinesApi
import java.lang.Exception

class HomeFragment : Fragment() {

    @InternalCoroutinesApi
    private lateinit var mViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        mViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.viewModel = mViewModel
        binding.lifecycleOwner = this
        // Inflate the layout for this fragment
        return binding.root
    }

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Navigation to Update Profile
        binding.profileEdit.setOnClickListener {
            val profile = mViewModel.getProfile().value
            if (profile != null){
                val action = HomeFragmentDirections.actionHomeFragmentToProfileFragment(profile)
                Navigation.findNavController(requireView()).navigate(action)
            }
        }

        // Navigation to Add Gold
        binding.addTransaction.setOnClickListener {
            val action: NavDirections = HomeFragmentDirections.actionHomeFragmentToBuyFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }

        // Choose Image from Gallery
        binding.profileImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }

        // Update Image at LiveData when changed
        mViewModel.getProfile().observe(viewLifecycleOwner, {
            binding.profileImage.setImageURI(Uri.parse(it.image))
        })

        // ViewPager + TabLayout
        binding.viewPager.adapter = HomeAdapter(this)
        val tab = TabLayoutMediator(binding.tabLayout, binding.viewPager) {tab, pos ->
            when (pos) {
                0 -> tab.text = "Wallet"
                1 -> tab.text = "Price"
                2 -> tab.text = "Transaction"
            }
        }
        tab.attach()
    }

    @InternalCoroutinesApi
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK){
            try {
                val imageuri: Uri? = data?.data
                var profile: Profile? = mViewModel.getProfile().value
                if (profile != null) {
                    profile.image = imageuri.toString()
                    mViewModel.updateProfile(profile)
                }
            } catch (e: Exception){
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}