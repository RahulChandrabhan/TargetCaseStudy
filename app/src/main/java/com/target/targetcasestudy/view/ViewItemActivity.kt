package com.target.targetcasestudy.view

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.target.targetcasestudy.R
import com.target.targetcasestudy.model.SingleItemData
import com.target.targetcasestudy.view.MainActivity.Companion.INTENT_ITEM_ID_KEY
import com.target.targetcasestudy.viewModel.ViewItemActivityViewModel

class ViewItemActivity : AppCompatActivity() {

    private lateinit var imageIv: ImageView
    private lateinit var priceTv: TextView
    private lateinit var titleTv: TextView
    private lateinit var descTv: TextView

    private lateinit var viewItemActivityViewModel: ViewItemActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_item)

        val itemId = intent.getIntExtra(INTENT_ITEM_ID_KEY, -1)

        initViews()

        viewItemActivityViewModel =
            ViewModelProviders.of(this).get(ViewItemActivityViewModel::class.java)

        if (itemId != -1)
            getItemDetails(itemId)
        else
            Toast.makeText(this, "No data found", Toast.LENGTH_LONG).show()


    } // End On-Create

    private fun getItemDetails(itemId: Int) {
        viewItemActivityViewModel.showProgressDialog(this)
        viewItemActivityViewModel.getSingleItemData(itemId).observe(this,
            Observer<SingleItemData> {
                setData(it as SingleItemData)
                viewItemActivityViewModel.dismissProgressDialog()
            })
    }

    private fun setData(singleItemData: SingleItemData) {
        Glide.with(this)
            .load(singleItemData.imageUrl)
            .centerCrop()
            .into(imageIv)
        priceTv.text = singleItemData.displayPrice
        titleTv.text = singleItemData.title
        descTv.text = singleItemData.description
    }

    private fun initViews() {
        imageIv = findViewById(R.id.act_view_item_image_iv)
        priceTv = findViewById(R.id.act_view_item_price_tv)
        titleTv = findViewById(R.id.act_view_item_title_tv)
        descTv = findViewById(R.id.act_view_item_description_tv)
    }

} // End Class