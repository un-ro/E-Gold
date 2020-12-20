package com.unero.e_gold.ui.tabitem.adapters

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.unero.e_gold.data.model.Transact
import com.unero.e_gold.databinding.ItemTransactionBinding

class TransactionAdapter(layoutResId: Int, data: MutableList<Transact>?) :
    BaseQuickAdapter<Transact, BaseViewHolder>(layoutResId, data) {
    override fun convert(holder: BaseViewHolder, item: Transact) {
        val bind: ItemTransactionBinding = DataBindingUtil.bind(holder.itemView)!!
        bind.item = item
        bind.executePendingBindings()
    }
}