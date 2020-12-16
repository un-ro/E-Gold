package com.unero.e_gold.ui.login

import android.app.Activity
import android.content.ContentResolver
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
import androidx.navigation.fragment.findNavController
import com.github.dhaval2404.imagepicker.ImagePicker
import com.github.dhaval2404.imagepicker.constant.ImageProvider
import com.unero.e_gold.R
import com.unero.e_gold.data.model.Account
import com.unero.e_gold.databinding.FragmentLoginBinding
import com.unero.e_gold.ui.viewmodel.AccountViewModel
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class LoginFragment : Fragment() {

    private lateinit var mViewModel: AccountViewModel
    private lateinit var binding: FragmentLoginBinding
    private var filePath: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        mViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
        binding.lifecycleOwner = this
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageProfile.setOnClickListener {
            ImagePicker.with(this)
                .provider(ImageProvider.BOTH)
                .crop()
                .start(102)
        }

        val defPicture = Uri.Builder()
            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .authority(resources.getResourcePackageName(R.drawable.account_default))
            .appendPath(resources.getResourceTypeName(R.drawable.account_default))
            .appendPath(resources.getResourceEntryName(R.drawable.account_default))
            .build()

        binding.btnCreate.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val username = binding.edtUsername.text.toString()

            if (email.isEmpty() || username.isEmpty()){
                Toasty.error(requireContext(), "Fill form first!", Toast.LENGTH_SHORT).show()
            } else {
                if (filePath.isEmpty()){
                    // Create Object then Insert to Database
                    mViewModel.add(Account(email, username, defPicture.toString()))
                    Toasty.success(requireContext(), "Your data SAVED!", Toast.LENGTH_SHORT).show()
                    // Navigate to Home
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                } else {
                    // Create Object then Insert to Database
                    mViewModel.add(Account(email, username, filePath))
                    Toasty.success(requireContext(), "Your data SAVED!", Toast.LENGTH_SHORT).show()
                    // Navigate to Home
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data
                binding.imageProfile.setImageURI(fileUri)
                //You can also get File Path from intent
                filePath = ImagePicker.getFilePath(data)!!
                Toasty.success(requireContext(), "$fileUri \n $filePath", Toast.LENGTH_SHORT).show()
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