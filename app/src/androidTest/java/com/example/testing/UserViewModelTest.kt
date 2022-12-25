package com.example.testing

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testing.localdatasource.User
import com.example.testing.localdatasource.UserDao
import com.example.testing.localdatasource.UserDatabase
import org.junit.After
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat


@RunWith(AndroidJUnit4::class)
class UserViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    lateinit var userDatabase: UserDatabase
    lateinit var userDao: UserDao
    lateinit var userViewModel: UserViewModel

    @Before
    fun setUp() {
        userDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), UserDatabase::class.java
        ).allowMainThreadQueries().build()
        userDao = userDatabase.userDao()
        userViewModel = UserViewModel(userDao)
    }

    @After
    fun closeDatabase() {
        userDatabase.close()
    }

    @Test
    fun `Insert_New_User_Find_InThe_Room`() {
        val newUser = User(uid = 55, "Hamza", "hamzaakram@gmail.com", "0310", false)
        userViewModel.addUser(newUser)
        userViewModel.getUsers()
        val findUser = userViewModel._users.getOrAwaitValue().find { it.uid == newUser.uid }
        assertThat(findUser).isNotNull()
    }

    @Test
    fun insertedUsersWillReturnByTheRoomDatabase() {
        val newUser1 = User(uid = 55, "Hamza", "hamzaakram@gmail.com", "0310", false)
        val newUser2 = User(uid = 52, "Ali", "hamzaakram.shyk17@gmail.com", "03103949574", true)
        val newUser3 = User(uid = 50, "Hamza", "hamzaakram@gmail.com", "0310", false)
        userViewModel.addUser(newUser1)
        userViewModel.addUser(newUser2)
        userViewModel.addUser(newUser3)
        userViewModel.getUsers()
        val returnUsers = userViewModel._users.getOrAwaitValue()
        assertThat(returnUsers.size).isEqualTo(3)
    }


}