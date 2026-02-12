package com.example.kotlin_getback_compose_lab1.data_class

import kotlinx.serialization.Serializable

@Serializable
sealed class Screens {

    @Serializable
    data class SignUp(val dummy: String = "") : Screens()

    @Serializable
    data class Login(
        val name: String,
        val password: String
    ) : Screens()

    @Serializable
    data class Home(
        val username: String
    ) : Screens()
}
