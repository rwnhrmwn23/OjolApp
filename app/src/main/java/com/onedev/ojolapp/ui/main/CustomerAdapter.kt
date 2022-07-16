package com.onedev.ojolapp.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onedev.ojolapp.core.domain.model.Customer
import com.onedev.ojolapp.databinding.LayoutListCustomerBinding

class CustomerAdapter : RecyclerView.Adapter<CustomerAdapter.HomeViewHolder>() {

    private val datas = ArrayList<Customer>()
    var onItemClick: ((Customer) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setListData(listData: List<Customer>?) {
        if (listData == null) return
        datas.clear()
        datas.addAll(listData)
        notifyDataSetChanged()
    }

    inner class HomeViewHolder(private val binding: LayoutListCustomerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Customer) {
            binding.apply {
                viewmodel = data
                executePendingBindings()

                itemView.setOnClickListener {
                    onItemClick?.invoke(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = LayoutListCustomerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size

}