package com.unero.e_gold.ui.tabitem.adapters

import android.view.View
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.unero.e_gold.data.model.Price
import com.unero.e_gold.databinding.ItemPriceBinding

class PriceAdapter(layoutResId: Int, data: MutableList<Price>?) :
    BaseQuickAdapter<Price, BaseViewHolder>(layoutResId, data), OnItemClickListener {
    override fun convert(holder: BaseViewHolder, item: Price) {
        val bind: ItemPriceBinding = DataBindingUtil.bind(holder.itemView)!!
        bind.item = item
        bind.executePendingBindings()
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        print("Something")
    }
}