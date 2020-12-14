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
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.unero.e_gold.R
import com.unero.e_gold.databinding.FragmentHomeBinding
import kotlinx.coroutines.InternalCoroutinesApi
import java.lang.Exception

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    @InternalCoroutinesApi
    private lateinit var mViewModel: HomeViewModel

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
        binding.lifecycleOwner = this
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Navigation to Update Profile
        binding.profileEdit.setOnClickListener {
        }

        // Update Image
        binding.profileImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK){
            try {
                val imageuri: Uri? = data!!.data
            } catch (e: Exception){
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}