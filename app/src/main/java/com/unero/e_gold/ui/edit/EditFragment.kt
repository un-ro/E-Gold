package com.unero.e_gold.ui.edit

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.github.dhaval2404.imagepicker.ImagePicker
import com.github.dhaval2404.imagepicker.constant.ImageProvider
import com.unero.e_gold.R
import com.unero.e_gold.data.model.Account
import com.unero.e_gold.data.viewmodel.AccountViewModel
import com.unero.e_gold.databinding.FragmentEditBinding
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class EditFragment : Fragment() {
    private val args by navArgs<EditFragmentArgs>()
    private var filePath: String = ""
    private lateinit var binding: FragmentEditBinding
    private lateinit var mViewModel: AccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit, container, false)
        mViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillForm()

        binding.imageProfile.setOnClickListener {
            ImagePicker.with(this)
                .provider(ImageProvider.BOTH)
                .crop()
                .start()
        }

        binding.btnUpdate.setOnClickListener {
            updateAccount()
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
            }
            ImagePicker.RESULT_ERROR -> {
                Toasty.error(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toasty.warning(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateAccount(){
        val username = binding.edtUsername.text.toString()
        val email = binding.edtEmail.text.toString()
        if (!(TextUtils.isEmpty(username) || TextUtils.isEmpty(email))) {
            if (filePath.isEmpty()) {
                mViewModel.update(
                    Account(
                        binding.edtEmail.text.toString(),
                        binding.edtUsername.text.toString(),
                        args.currentAccount.image
                    )
                )
                Toasty.success(requireContext(), "Update Profile Success", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_editFragment_to_homeFragment)
            } else {
                mViewModel.update(
                    Account(
                        binding.edtEmail.text.toString(),
                        binding.edtUsername.text.toString(),
                        filePath
                    )
                )
                Toasty.success(requireContext(), "Update Profile Success", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_editFragment_to_homeFragment)
            }
        } else {
            Toasty.warning(requireContext(), "Please fill form!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fillForm() {
        if (args.currentAccount.image.isEmpty()){
            binding.edtUsername.setText(args.currentAccount.username)
            binding.edtEmail.setText(args.currentAccount.email)
            binding.imageProfile.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.default_account))
        } else{
            binding.edtUsername.setText(args.currentAccount.username)
            binding.edtEmail.setText(args.currentAccount.email)
            binding.imageProfile.setImageBitmap(pathToImage(args.currentAccount.image))
        }
    }

    private fun pathToImage(image: String): Bitmap {
        return BitmapFactory.decodeFile(image)
    }
}