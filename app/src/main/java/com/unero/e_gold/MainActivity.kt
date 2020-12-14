package com.unero.e_gold

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(findNavController(R.id.host))
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.host)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}