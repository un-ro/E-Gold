package com.unero.e_gold.ui.splash

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.unero.e_gold.R
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

@InternalCoroutinesApi
class SplashFragment : Fragment(), CoroutineScope {

    private lateinit var sharedPref: SharedPreferences
    private lateinit var sharedEditor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = requireContext().getSharedPreferences("GoldSharedPreference", 0)
        sharedEditor = sharedPref.edit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    // Coroutine Scope
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        launch {
            delay(2500) // Delay for 2.5s
            // Coroutine in Main / UI Thread
            withContext(Dispatchers.Main){
                // Navigation
                if (firstTime()){
                    Toasty.info(requireContext(), "Create your data first", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                } else {
                    Toasty.normal(requireContext(), "Welcome", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                }
            }
        }
    }

    private fun firstTime(): Boolean {
        return if (sharedPref.getBoolean("firstTime", true)){
            sharedEditor.putBoolean("firstTime", false)
            sharedEditor.commit()
            sharedEditor.apply()
            true
        } else {
            false
        }
    }
}