package com.unero.e_gold.ui.home

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.tabs.TabLayoutMediator
import com.unero.e_gold.R
import com.unero.e_gold.data.viewmodel.AccountViewModel
import com.unero.e_gold.databinding.FragmentHomeBinding
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var mViewModel: AccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        mViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
        binding.vieModel = mViewModel
        binding.lifecycleOwner = this
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // viewModel ke UI
        mViewModel.accounts.observe(viewLifecycleOwner, {
            if (it.image.isEmpty()){
                binding.txtUsername.text = it.username
                binding.txtEmail.text = it.email
                binding.profileImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.default_account))
            } else{
                binding.txtUsername.text = it.username
                binding.txtEmail.text = it.email
                binding.profileImage.setImageBitmap(pathToImage(it.image))
            }
        })

        binding.cvAccount.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToEditFragment(mViewModel.accounts.value!!)
            findNavController().navigate(action)
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

    private fun pathToImage(image: String): Bitmap {
        return BitmapFactory.decodeFile(image)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            RESULT_OK -> {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data
                binding.profileImage.setImageURI(fileUri)
                //You can also get File Path from intent
                val filePath:String = ImagePicker.getFilePath(data)!!
                Toasty.success(requireContext(), filePath, Toast.LENGTH_SHORT).show()
            }
            ImagePicker.RESULT_ERROR -> {
                Toasty.error(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toasty.warning(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }
}