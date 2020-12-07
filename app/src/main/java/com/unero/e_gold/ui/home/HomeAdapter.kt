package com.unero.e_gold.ui.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.unero.e_gold.ui.PriceFragment
import com.unero.e_gold.ui.TransactionFragment
import com.unero.e_gold.ui.WalletFragment

class HomeAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> WalletFragment()
            1 -> PriceFragment()
            2 -> TransactionFragment()
            else -> {
                WalletFragment()
            }
        }
    }
}