package com.onedev.ojolapp.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onedev.ojolapp.core.domain.model.Driver
import com.onedev.ojolapp.databinding.LayoutListDriverBinding

class DriverAdapter : RecyclerView.Adapter<DriverAdapter.HomeViewHolder>() {

    private val datas = ArrayList<Driver>()
    var onItemClick: ((Driver) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setListData(listData: List<Driver>?) {
        if (listData == null) return
        datas.clear()
        datas.addAll(listData)
        notifyDataSetChanged()
    }

    inner class HomeViewHolder(private val binding: LayoutListDriverBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Driver) {
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
        val binding = LayoutListDriverBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size

}