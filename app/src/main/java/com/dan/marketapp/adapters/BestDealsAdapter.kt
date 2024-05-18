package com.dan.marketapp.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dan.marketapp.data.Product
import com.dan.marketapp.databinding.BestDealsRvItemBinding
class BestDealsAdapter: RecyclerView.Adapter<BestDealsAdapter.BestDealsViewHolder>() {
    inner class BestDealsViewHolder(private val binding: BestDealsRvItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product){
            binding.apply {
                Glide.with(itemView).load(product.images[0]).into(imgBestDeal)
                product.offerPercentage?.let {
                    val remainingPricePercentage = 1f - it
                    val remainingPrice = product.price * remainingPricePercentage
                    tvNewPrice.text = "$ ${String.format("%.2f", remainingPrice)}"
                }
                tvOldPrice.text = "$ ${product.price}"
                //tvOldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                tvDealProductName.text = product.name
            }
        }
    }
    private val diffCallback = object: DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id ==newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestDealsViewHolder {
        return BestDealsViewHolder(
            BestDealsRvItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: BestDealsViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.bind(product)
    }
}