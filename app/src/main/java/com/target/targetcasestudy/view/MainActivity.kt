package com.target.targetcasestudy.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.target.targetcasestudy.R
import com.target.targetcasestudy.adapter.AllDealsAdapter
import com.target.targetcasestudy.interfaces.AllDealsAdapterOnItemClick
import com.target.targetcasestudy.model.AllDealsData
import com.target.targetcasestudy.old.ui.payment.PaymentDialogFragment
import com.target.targetcasestudy.viewModel.MainActivityViewModel

class MainActivity : AppCompatActivity(), AllDealsAdapterOnItemClick {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var allDealsDataRv: RecyclerView
    private lateinit var allDealsAdapter: AllDealsAdapter
    private var allDealsDataList = arrayListOf<AllDealsData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        allDealsDataRv = findViewById(R.id.act_main_rv)
        allDealsDataRv.addItemDecoration(
            DividerItemDecoration(
                allDealsDataRv.context,
                DividerItemDecoration.VERTICAL
            )
        )
        allDealsDataRv.layoutManager = linearLayoutManager
        allDealsAdapter = AllDealsAdapter(allDealsDataList, this)
        allDealsDataRv.adapter = allDealsAdapter
        getAllDeals()

    } // End On-Create

    private fun getAllDeals() {
        mainActivityViewModel.showProgressDialog(this)
        mainActivityViewModel.getAllDealsData().observe(this,
            Observer<List<AllDealsData>> {
                allDealsDataList.clear()
                allDealsDataList.addAll(it as ArrayList<AllDealsData>)
                allDealsAdapter.notifyDataSetChanged()
                mainActivityViewModel.dismissProgressDialog()
            })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.credit_card -> {
                PaymentDialogFragment().show(supportFragmentManager, "CreditCardValidation")
                true
            }
            else -> false
        }
    }

    companion object {
        private const val TAG = "MainActivity"
        const val INTENT_ITEM_ID_KEY = "intent_item_id_key"
    }

    override fun onClick(allDealsData: AllDealsData) {
        startActivity(
            Intent(this, ViewItemActivity::class.java).putExtra(
                INTENT_ITEM_ID_KEY,
                allDealsData.itemId
            )
        )
    }

} // End Class
