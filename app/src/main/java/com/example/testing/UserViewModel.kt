package com.example.testing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testing.localdatasource.User
import com.example.testing.localdatasource.UserDao
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.internal.artificialFrame
import kotlin.coroutines.CoroutineContext

class UserViewModel(private val userDao: UserDao) : ViewModel() {

    private var users = MutableLiveData<List<User>>()
    var _users: LiveData<List<User>> = users

    fun addUser(user: User): LiveData<UIEvent> {
        CoroutineScope(Dispatchers.IO).launch {
            userDao.insertAll(user)
        }
        return MutableLiveData(UIEvent.Success("New User has been Added"))
    }

    fun searchByIds(ids: List<Int>): LiveData<List<User>> {
        var result: List<User>? = null
        CoroutineScope(Dispatchers.IO).launch {
            result = userDao.loadAllByIds(ids)
        }
        return MutableLiveData(result)

    }

    fun getUsers() {
        CoroutineScope(Dispatchers.Main).launch {
            userDao.getFlowAllUsers().collectLatest {
                users.value = it
            }
        }

    }


}