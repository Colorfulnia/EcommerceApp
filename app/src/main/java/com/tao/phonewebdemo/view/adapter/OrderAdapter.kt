package com.tao.phonewebdemo.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tao.phonewebdemo.R
import com.tao.phonewebdemo.databinding.ItemOrderBinding
import com.tao.phonewebdemo.model.local.Cart
import com.tao.phonewebdemo.model.remote.data.Constant

class OrderAdapter(private val list: List<Cart>, private val onDeleteItem: (Cart) -> Unit): RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    class OrderViewHolder(private val binding: ItemOrderBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(cart: Cart) {
            binding.apply {
                Glide.with(root.context)
                    .load(Constant.Constants.BASE_IMAGE_URL + cart.imageURL)
                    .placeholder(R.drawable.phoneicon)
                    .error(R.drawable.phoneicon)
                    .fallback(R.drawable.phoneicon)
                    .into(imageCartProductOrder)
                productCartNameOrder.text = cart.name
                cartProductPriceOrder.text = "$ ${cart.price}"
                quantityCartOrder.text = cart.quantity.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemOrderBinding.inflate(layoutInflater, parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val cart = list[position]
        holder.bind(cart)

        holder.itemView.setOnLongClickListener {
            onDeleteItem(cart)
            true
        }
    }

    override fun getItemCount(): Int = list.size
}