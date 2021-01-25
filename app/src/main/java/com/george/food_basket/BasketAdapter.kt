package com.george.food_basket

import android.icu.text.LocaleDisplayNames
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.george.food_basket.db.ListItem

class BasketAdapter(productList:ArrayList<ListItem>) : RecyclerView.Adapter<BasketAdapter.Holder>() {

    var listProd = productList

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleDate:TextView = itemView.findViewById(R.id.textAdapter)

        fun  setData(title:String){

            titleDate.text = title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
       val inflater = LayoutInflater.from(parent.context)
        return Holder(inflater.inflate(R.layout.basket_text_adapter, parent, false))
    }

    override fun getItemCount(): Int {
       return listProd.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setData(listProd.get(position).product)
    }

    fun updateAdapter(listItem:List<ListItem>){
        listProd.clear()
        listProd.addAll(listItem)
        notifyDataSetChanged()
    }
}