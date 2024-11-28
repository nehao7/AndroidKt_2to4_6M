package com.example.androidkt_2to4_6m.Realtimedatabase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidkt_2to4_6m.Realtimedatabase.firebase.ClickInterface
import com.example.androidkt_2to4_6m.Realtimedatabase.firebase.model.MenuModel
import com.example.androidkt_2to4_6m.databinding.AdapterMenuBinding

class MenuAdapter(var userList:ArrayList<MenuModel>, var clickInterface: ClickInterface
): RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: AdapterMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(position: Int) {
            val menu = userList[position]
            binding.tvMenuName.text = menu.name
            binding.tvPriceShow.text = menu.price

            binding.ivEdit.setOnClickListener {
                clickInterface.editClick(menu, position)
            }
            binding.ivDelete.setOnClickListener {
                clickInterface.deleteClick(menu, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(position)
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}