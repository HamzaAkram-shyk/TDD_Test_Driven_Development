package com.example.testing

import java.util.regex.Pattern

object Validator {

    private val registeredUsers= listOf("Ali","Saif","Adnan")

    fun getProfile(profile: Any?): Any? {
        return profile
    }

    fun isCredentialsValid(name: String, email: String): Boolean {
        return (name.isNotEmpty() && email.isNotEmpty())
    }


    fun isValidCredentials(email: String, password: String): Boolean {
        val pattern: Pattern = Pattern.compile(".+@.+\\.[a-z]+")
        if (email.isEmpty() || password.isEmpty())
            return false
        if (!pattern.matcher(email).matches()) return false

        if (password.length < 8) return false

        return true

    }

    fun checkBraces(string: String):Boolean{
        return  string.count { it== '(' } == string.count { it == ')' }
    }


    fun registerNewUser(name:String):String{
        if(name in registeredUsers)
            return "User is Already Register"


     return "Successfully"
    }


}