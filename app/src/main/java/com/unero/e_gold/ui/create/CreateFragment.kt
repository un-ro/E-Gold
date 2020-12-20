package com.unero.e_gold.ui.create

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.github.dhaval2404.imagepicker.ImagePicker
import com.github.dhaval2404.imagepicker.constant.ImageProvider
import com.unero.e_gold.R
import com.unero.e_gold.data.model.Account
import com.unero.e_gold.data.viewmodel.AccountViewModel
import com.unero.e_gold.databinding.FragmentCreateBinding
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class CreateFragment : Fragment() {

    private lateinit var mViewModel: AccountViewModel
    private lateinit var binding: FragmentCreateBinding
    private var filePath: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create, container, false)
        binding.lifecycleOwner = this
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Great Library love it
        // Use both, camera / gallery. User can pick.
        // And Compress it to just 1MB if >= 1MB
        binding.imageProfile.setOnClickListener {
            ImagePicker.with(this)
                .provider(ImageProvider.BOTH)
                .compress(1024)
                .crop()
                .start()
        }

        binding.btnCreate.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val username = binding.edtUsername.text.toString()

            if (email.isEmpty() || username.isEmpty()){
                Toasty.error(requireContext(), "Fill form first!", Toast.LENGTH_SHORT).show()
            } else {
                if (filePath.isEmpty()){
                    // Create Object without Image path then Insert to Database
                    mViewModel.add(Account(email, username, filePath))
                    Toasty.success(requireContext(), "Account Saved!", Toast.LENGTH_SHORT).show()
                    // Navigate to Home
                    findNavController().navigate(R.id.action_createFragment_to_homeFragment)
                } else {
                    // Create Object with Image path then Insert to Database
                    mViewModel.add(Account(email, username, filePath))
                    Toasty.success(requireContext(), "Account Saved!", Toast.LENGTH_SHORT).show()
                    // Navigate to Home
                    findNavController().navigate(R.id.action_createFragment_to_homeFragment)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                // Use Uri to set image into CircleImage
                val fileUri = data?.data
                binding.imageProfile.setImageURI(fileUri)
                // Save path to variable then pass it to Object
                filePath = ImagePicker.getFilePath(data)!!
                Toasty.success(requireContext(), "Image Successfully saved", Toast.LENGTH_SHORT).show()
            }
            ImagePicker.RESULT_ERROR -> {
                // Got error only in emulator (Don't know why)
                Toasty.error(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toasty.warning(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }
}