package com.target.targetcasestudy.viewModel

import android.app.Application
import android.app.ProgressDialog
import android.content.Context
import androidx.lifecycle.*
import com.target.targetcasestudy.model.AllDealsData
import com.target.targetcasestudy.model.ItemsRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application),
    LifecycleObserver {

    private var itemsRepository: ItemsRepository = ItemsRepository(application)
    private lateinit var progressDialog: ProgressDialog

    fun getAllDealsData(): LiveData<List<AllDealsData>> {
        return itemsRepository.getAllDeals()
    }

    fun showProgressDialog(context: Context) {
        progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Please Wait")
        progressDialog.show()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun dismissProgressDialog() {
        if (progressDialog.isShowing)
            progressDialog.dismiss()
    }

}