package com.example.testing

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testing.localdatasource.User
import com.example.testing.localdatasource.UserDao
import com.example.testing.localdatasource.UserDatabase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class UserDatabaseTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    lateinit var userDatabase: UserDatabase
    lateinit var userDao: UserDao

    @Before
    fun setUp() {
        userDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), UserDatabase::class.java
        ).allowMainThreadQueries().build()
        userDao = userDatabase.userDao()
    }

    @After
    fun closeDatabase() {
        userDatabase.close()
    }

    @Test
    fun databaseContainsNewInsertedUser() = runBlocking {
        val newUser = User(
            uid = 19,
            name = "Hamza",
            email = "hamzaakram.shyk17@gmail.com",
            number = "0310394",
            isPremiumUser = true
        )
        userDao.insertAll(newUser)
        val users = userDao.getAllUsers().getOrAwaitValue()
        assertThat(users).contains(newUser)
    }


    @Test
    fun findUserByNameAndNumberGivesOnlyTwoUsers() = runBlocking {
        val newUser1 = User(
            uid = 20,
            name = "Hamza",
            email = "hamzaakram.shyk17@gmail.com",
            number = "0310394",
            isPremiumUser = true
        )
        val newUser2 = User(
            uid = 21,
            name = "Hamza",
            email = "hamzaakram.shyk17@gmail.com",
            number = "0310394",
            isPremiumUser = true
        )
        val newUser3 = User(
            uid = 22,
            name = "Hamza",
            email = "hamzaakram.shyk17@gmail.com",
            number = "0310394",
            isPremiumUser = true
        )
        userDao.insertAll(newUser1)
        userDao.insertAll(newUser2)
        userDao.insertAll(newUser3)
        val users = userDao.findByName("Hamza", "0310394")
        assertThat(users.size).isEqualTo(2)
    }

}