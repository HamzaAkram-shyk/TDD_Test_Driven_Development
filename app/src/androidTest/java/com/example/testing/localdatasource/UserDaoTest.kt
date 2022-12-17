package com.example.testing.localdatasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.testing.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class UserDaoTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var userDatabase: UserDatabase
    private lateinit var userDao: UserDao

    @Before
    fun setUp() {
        userDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), UserDatabase::class.java
        ).allowMainThreadQueries().build()
        userDao = userDatabase.userDao()
    }


    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        userDatabase.close()
    }


    @Test
    @Throws(Exception::class)
    fun databaseContainsNewInsertedUser() = runTest {
        val newUser = User(
            uid = 10,
            name = "Hamza",
            email = "hamzaakram.shyk17@gmail.com",
            number = "0310394",
            isPremiumUser = true
        )
        userDao.insertAll(newUser)
        val users = userDao.getAllUsers().getOrAwaitValue()
        assertThat(users).contains(newUser)
    }


}