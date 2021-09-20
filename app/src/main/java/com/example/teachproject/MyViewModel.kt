package com.example.teachproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel() : ViewModel() {

    private val model: MyModel = MyModel.create()

    private val items: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>().also {
            loadItems()
        }
    }

    fun getUsers(): LiveData<List<String>> {
        return items
    }

    private fun loadItems(): List<String> {
        return model.refreshData()
    }
}