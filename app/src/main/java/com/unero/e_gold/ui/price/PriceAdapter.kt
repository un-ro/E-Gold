package com.unero.e_gold.ui.price

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.unero.e_gold.R
import com.unero.e_gold.databinding.ItemPriceBinding
import com.unero.e_gold.models.Price

class PriceAdapter(layoutResId: Int, data: ArrayList<Price>): BaseQuickAdapter<Price, BaseViewHolder>(layoutResId, data){

    // Adapter never been easy like this.
    override fun convert(holder: BaseViewHolder, item: Price) {
        val bind: ItemPriceBinding? = holder.getBinding()
        if (bind != null){
            bind.price = item
            bind.executePendingBindings()
        }
    }

    // Binding item Layout
    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)
        DataBindingUtil.bind<ItemPriceBinding>(viewHolder.itemView)
    }

}