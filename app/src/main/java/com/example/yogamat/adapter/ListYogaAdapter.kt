package com.example.yogamat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.example.yogamat.R
import com.example.yogamat.databinding.YogaListItemBinding
import com.example.yogamat.model.Yoga
import com.squareup.picasso.Picasso

class ListYogaAdapter(
    private val yogaList: List<Yoga>,
    private val action: OnClickAction,
    private val context: Context
): RecyclerView.Adapter<ListYogaAdapter.ListYogaViewHolder>() {

    lateinit var binding: YogaListItemBinding

    inner class ListYogaViewHolder(binding: YogaListItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bindJob(yoga: Yoga, action: OnClickAction) {

            binding.deleteButton.visibility = View.GONE
            binding.yogaTitle.text = yoga.title
            binding.yogaDetails.text = yoga.details
            val currentImageResId = context.resources.getIdentifier(yoga.image, "drawable", context.packageName)
            Picasso.get().load(currentImageResId).resize(400, 350).into(binding.yogaListImageview)

            binding.root.setOnClickListener {
                action.onCLick(yoga.id)
            }

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListYogaViewHolder {
        binding = YogaListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ListYogaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListYogaViewHolder, position: Int) {
        val yoga = yogaList[position]
        holder.bindJob(yoga, action)
    }

    override fun getItemCount(): Int {
        return yogaList.size
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}