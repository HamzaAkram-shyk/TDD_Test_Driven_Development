package com.example.testing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.testing.localdatasource.DatabaseBuilder
import com.example.testing.localdatasource.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val user = User(3, "", "", "", true)
//        CoroutineScope(Dispatchers.IO).launch {
//            DatabaseBuilder.getInstance(applicationContext).userDao().insertAll(user)
//
//        }
//        DatabaseBuilder.getInstance(applicationContext).userDao().getAllUsers()
//                .observe(this@MainActivity) {
//                   // Toast.makeText(applicationContext, "$it", Toast.LENGTH_SHORT).show()
//                    Log.d("Data", "$it")
//                }



    }
}