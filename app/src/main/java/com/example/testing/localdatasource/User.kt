package com.example.testing.localdatasource

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    val name: String,
    val email: String,
    val number: String,
    val isPremiumUser: Boolean,
)
