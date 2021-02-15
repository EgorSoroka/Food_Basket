package com.george.food_basket

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.george.food_basket.db.DbManager
import com.george.food_basket.db.ListItem

class BasketAdapter(productList: ArrayList<ListItem>, contextB: Context) :
    RecyclerView.Adapter<BasketAdapter.Holder>() {

    var listProd = productList
    private var contextB = contextB

    class Holder(itemView: View, contextV: Context) : RecyclerView.ViewHolder(itemView) {
        val titleDate: TextView = itemView.findViewById(R.id.textAdapter)
        val titleQuantity: TextView = itemView.findViewById(R.id.textQuantity)
        val context = contextV

        fun setData(title: ListItem) {
            titleDate.text = title.product
            titleQuantity.text = title.quantity.toString()
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder(inflater.inflate(R.layout.basket_text_adapter, parent, false), contextB)
    }

    override fun getItemCount(): Int {
        return listProd.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setData(listProd.get(position))
    }

    fun updateAdapter(listItem: List<ListItem>) {
        listProd.clear()
        listProd.addAll(listItem)
        notifyDataSetChanged()
    }

    fun removeItem(pos: Int, dbManager: DbManager) {
        dbManager.deleteOfDB(listProd[pos].id.toString())
        listProd.removeAt(pos)
        notifyItemRangeChanged(0, listProd.size)
        notifyItemRemoved(pos)
    }




}