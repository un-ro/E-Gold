package com.unero.e_gold.ui.tabitem.adapters

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.unero.e_gold.data.model.Price
import com.unero.e_gold.databinding.FragmentWalletBinding
import com.unero.e_gold.databinding.ItemWalletBinding

class WalletAdapter(layoutResId: Int, data: MutableList<Price>?): BaseQuickAdapter<Price, BaseViewHolder>(layoutResId, data) {
    override fun convert(holder: BaseViewHolder, item: Price) {
        val bind: ItemWalletBinding = DataBindingUtil.bind(holder.itemView)!!
        bind.item = item
        bind.executePendingBindings()
    }
}