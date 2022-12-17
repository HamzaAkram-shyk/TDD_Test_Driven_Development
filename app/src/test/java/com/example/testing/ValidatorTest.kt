package com.example.testing

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class ValidatorTest {

    @Test
    fun `When Profile is Null`() {
        val profile = Pair(2, 3)
        val result = Validator.getProfile(profile)
        assertThat(result).isNull()
    }


    @Test
    fun `Are Credentials Valid`() {
        val name = "Hamza"
        val email = "ededed"
        val result = Validator.isCredentialsValid(name, email)
        assertThat(result).isTrue()
    }


    @Test
    fun `Invalid Email Gives Error`() {
        val email = "hamza123gmail.com"
        val password = "12w232"
        val result = Validator.isValidCredentials(email, password)
        assertThat(result).isFalse()
    }

    @Test
    fun `Empty Email & Password Gives False`() {
        val email = ""
        val password = ""
        val result = Validator.isValidCredentials(email, password)
        assertThat(result).isFalse()
    }

    @Test
    fun `Password Length should be greater than 7`() {
        val email = "hamza@gmail.com"
        val password = "hamza031039"
        val result = Validator.isValidCredentials(email, password)
        assertThat(result).isTrue()
    }

    @Test
    fun `UnBalance Braces Gives False`(){
        val result=Validator.checkBraces("(()")
        assertThat(result).isFalse()
    }

    @Test
    fun `New User Registered Successfully`(){
        val message= Validator.registerNewUser("Hamza")
        assertThat(message).isEqualTo("Successfully")
    }


}