package com.example.messengerappdemo.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.messengerappdemo.model.Users

class SearchViewModel : ViewModel(){

    private var _list = MutableLiveData<List<Users>>()

    val list: LiveData<List<Users>>
        get() = _list

    init {
        loadUsers()
    }

    fun loadUsers() {
        _list.postValue(userListData())
    }

    fun performQuery(
        query: String,
    ) {
        val filteredList = ArrayList<Users>()
        userListData().forEach { actors ->
            if (actors.userName.lowercase().contains(query.lowercase())) {
                filteredList.add(Users(actors.userName))
            }
        }
        _list.postValue(filteredList)
    }

    private fun userListData(): List<Users>  {

        val data = listOf("Adele Exarchopoulos", "timothee chalamet", "Al Pacino")
        val usersList = ArrayList<Users>()
        data.forEach{
            usersList.add(Users(it))
        }
        return usersList
    }

}