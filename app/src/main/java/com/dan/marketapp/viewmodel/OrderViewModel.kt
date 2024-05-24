package com.dan.marketapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dan.marketapp.data.order.Order
import com.dan.marketapp.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
):ViewModel() {

    private val _order = MutableStateFlow<Resource<Order>>(Resource.Unspecifed())
    val order = _order.asStateFlow()

    fun placeOrder(order: Order) {
        viewModelScope.launch {
            _order.emit(Resource.Loading())
        }
        // runBatch для записи/перезаписи из БД  transition - для транзакции записи/перезаписи и чтение из БД
        firestore.runBatch { batch ->
            // Длбавляем заказ в заказы пользователя
            // добавляем заказ в коллекцию всех заказов
            // удаляем все продукты из корзины

            firestore.collection("user").document(auth.uid!!).collection("orders").document()
                .set(order)

            // добавляем заказ в коллекцию всех заказов
            firestore.collection("orders").document().set(order)
            // удаляем все продукты из корзины
            firestore.collection("user").document(auth.uid!!).collection("cart").get()
                .addOnSuccessListener {
                    for (document in it) {
                        it.documents.forEach {
                            it.reference.delete()
                        }
                    }

                }
        }.addOnSuccessListener {
            viewModelScope.launch {
                _order.emit(Resource.Success(order))
            }
        }.addOnFailureListener {
            viewModelScope.launch {
                _order.emit(Resource.Error(it.message.toString()))
            }
        }
    }

}