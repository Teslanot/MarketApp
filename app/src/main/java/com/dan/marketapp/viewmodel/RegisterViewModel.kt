package com.dan.marketapp.viewmodel

import androidx.lifecycle.ViewModel
import com.dan.marketapp.data.User
import com.dan.marketapp.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth)
    : ViewModel(){

    private val _register = MutableStateFlow<Resource<FirebaseUser>>(Resource.Unspecifed())
    val register: Flow<Resource<FirebaseUser>> =  _register
    fun createUserWithEmailAndPassword(user: User, password:String){
        runBlocking {
            _register.emit(Resource.Loading())
        }
        firebaseAuth.createUserWithEmailAndPassword(user.email,password)
            .addOnSuccessListener {
                it.user?.let{
                    _register.value = Resource.Success(it)
                }
            }.addOnFailureListener{
                _register.value = Resource.Error(it.message.toString())
            }
    }
}