package com.example.fortytworace.ui.businesslist.detailbusiness

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.BusinessReview
import com.example.fortytworace.R
import com.example.fortytworace.databinding.ItemListBusinessesBinding
import com.example.fortytworace.databinding.ItemListCommentBinding
import com.example.fortytworace.helper.loadImage

class DetailBusinessReviewAdapter : RecyclerView.Adapter<DetailBusinessReviewAdapter.UserViewHolder>(){

    var onItemClick: ((BusinessReview) -> Unit)? = null

    private val mData = ArrayList<BusinessReview>()

    fun setData(newListData: List<BusinessReview>?) {
        if (newListData == null) return
        mData.clear()
        mData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): UserViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_comment, parent, false)
        return UserViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListCommentBinding.bind(itemView)
        fun bind(data: BusinessReview) {
            with(binding) {

                // concat string
                tvNameComment.text = data.userName
                tvDateComment.text = data.timeCreated
                ivProfilepicComment.loadImage(itemView.context,data.userImageUrl)         // masih belom ada profile pic

            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(mData[adapterPosition])
            }
        }


    }

}