package com.example.newsapplication

import androidx.lifecycle.ViewModel
import com.example.news_api.model.NewsResponse

class NewsFragmentViewModel() : ViewModel() {

    var response: NewsResponse? = null
    var noOfItem: Int = 1

    fun saveResponse(newsResponse: NewsResponse?) {
        response = newsResponse
    }

    fun changeNoOfItem(newNo: Int) {
        noOfItem = newNo
    }

    fun getSavedResponse(): NewsResponse? {
        return response
    }

    @JvmName("getNoOfItem1")
    fun getNoOfItem(): Int {
        return noOfItem
    }

}