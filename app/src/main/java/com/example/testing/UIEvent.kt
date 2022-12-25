package com.example.testing

import com.example.testing.localdatasource.User

sealed class UIEvent{
    class Success(message: String):UIEvent()
    class Error(message:String):UIEvent()
    class UserFound(var users:List<User>):UIEvent()

}
