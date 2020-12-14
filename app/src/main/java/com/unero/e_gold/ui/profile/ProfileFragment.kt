package com.unero.e_gold.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.unero.e_gold.R
import com.unero.e_gold.databinding.FragmentProfileBinding
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var mViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        mViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        binding.lifecycleOwner = this
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.profileLiveData().observe(viewLifecycleOwner, {
            if (it != null){
                val action: NavDirections = ProfileFragmentDirections.actionProfileFragmentToHomeFragment()
                Navigation.findNavController(requireView()).navigate(action)
            }
        })
    }
}