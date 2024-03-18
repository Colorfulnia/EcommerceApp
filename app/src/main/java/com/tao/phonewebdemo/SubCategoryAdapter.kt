package com.tao.phonewebdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tao.phonewebdemo.model.SubCategoryItem
import com.squareup.picasso.Picasso
class SubCategoryAdapter(
    private val items: List<SubCategoryItem>,
    private val onAddToCartClicked: (SubCategoryItem) -> Unit
) : RecyclerView.Adapter<SubCategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sub_category_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, onAddToCartClicked)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: SubCategoryItem, onAddToCartClicked: (SubCategoryItem) -> Unit) {
            view.findViewById<TextView>(R.id.textViewItemName).text = item.name
            view.findViewById<TextView>(R.id.textViewItemPrice).text = view.context.getString(R.string.item_price_format, item.price)
//            view.findViewById<TextView>(R.id.itemDescription).text = item.description
            Picasso.get().load(item.imageUrl).into(view.findViewById<ImageView>(R.id.imageViewItem))
            view.findViewById<Button>(R.id.buttonAddToCart).setOnClickListener {
                onAddToCartClicked(item)
            }
        }
    }
}