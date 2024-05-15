package com.dan.marketapp.util

import android.util.Patterns
import java.util.regex.Pattern

fun validateEmail(email:String):RegisterValidation{
    if (email.isEmpty())
        return RegisterValidation.Failed("Email cannot be empty")

    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        return RegisterValidation.Failed("Wrong email address. Entre you email address")

    return RegisterValidation.Success
}
fun validatePassword(password:String):RegisterValidation{
    if (password.isEmpty())
        return RegisterValidation.Failed("Password cannot be empty")

    if (password.length<6)
        return RegisterValidation.Failed("Password should be contains 6 char")

    return RegisterValidation.Success
}