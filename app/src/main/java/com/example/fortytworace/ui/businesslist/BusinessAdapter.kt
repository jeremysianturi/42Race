package com.example.fortytworace.ui.businesslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.Business
import com.example.fortytworace.R
import com.example.fortytworace.databinding.ItemListBusinessesBinding
import com.example.fortytworace.helper.loadImage
import timber.log.Timber

class BusinessAdapter : RecyclerView.Adapter<BusinessAdapter.UserViewHolder>() {

    var onItemClick: ((Business) -> Unit)? = null

    private val mData = ArrayList<Business>()

    fun setData(newListData: List<Business>?) {
        if (newListData == null) return
        mData.clear()
        mData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): BusinessAdapter.UserViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_businesses, parent, false)
        return UserViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: BusinessAdapter.UserViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListBusinessesBinding.bind(itemView)
        fun bind(data: Business) {
            with(binding) {

                Timber.d("check value categories title adapter : ${data.categoriesTitle}")
                // concat string
                tvContenttitle.text = "cuisine type: ${data.categoriesTitle}"
                tvNameWahana.text = "Name: ${data.name}"
                tvDateWahana.text = "Rating: ${data.rating}"
                tvPostalCode.text = "Postal Code: ${data.locationZipCode}"
                tvDistance.text = "Distance: ${data.distance}"
                ivProfilepic.loadImage(itemView.context,data.imageUrl)
//                ivContentWahana.loadImage(itemView.context,data.thumbnail)



            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(mData[adapterPosition])
            }
        }


    }

}