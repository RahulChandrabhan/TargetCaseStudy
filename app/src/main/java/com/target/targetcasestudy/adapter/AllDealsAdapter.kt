package com.target.targetcasestudy.adapter

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.target.targetcasestudy.R
import com.target.targetcasestudy.interfaces.AllDealsAdapterOnItemClick
import com.target.targetcasestudy.model.AllDealsData


class AllDealsAdapter(
    private val allDealsList: List<AllDealsData>,
    private val allDealsAdapterOnItemClick: AllDealsAdapterOnItemClick
) :
    RecyclerView.Adapter<AllDealsAdapter.AllDealsAdapterViewHolder>() {

    private val coloredSpannable: Spannable = SpannableString("ship or")

    init {
        initSpannable()
    }

    private fun initSpannable() {
        coloredSpannable.setSpan(
            ForegroundColorSpan(Color.GRAY),
            5,
            coloredSpannable.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllDealsAdapterViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_items_all_deals, parent, false)
        return AllDealsAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return allDealsList.size
    }

    override fun onBindViewHolder(holder: AllDealsAdapterViewHolder, position: Int) {
        val allDealsData: AllDealsData = allDealsList[position]
        holder.coloredHardcodedTv.text = coloredSpannable
        Glide.with(allDealsAdapterOnItemClick as Context)
            .load(allDealsData.imageUrl)
            .centerCrop()
            .into(holder.itemImageIv)
        holder.itemTitleTv.text = allDealsData.title
        holder.itemPriceTv.text = allDealsData.displayPrice
        holder.itemAisleTv.text = allDealsData.aisle
        holder.itemView.setOnClickListener {
            allDealsAdapterOnItemClick.onClick(allDealsData)
        }
    }

    class AllDealsAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImageIv: ImageView = itemView.findViewById(R.id.rv_items_all_deals_image_iv)
        val itemTitleTv: TextView = itemView.findViewById(R.id.rv_items_all_deals_name_tv)
        val itemPriceTv: TextView = itemView.findViewById(R.id.rv_items_all_deals_price_tv)
        val itemAisleTv: TextView = itemView.findViewById(R.id.rv_items_all_deals_aisle_tv)
        val coloredHardcodedTv: TextView =
            itemView.findViewById(R.id.rv_items_all_deals_colored_hardcoded_tv)
    }

}