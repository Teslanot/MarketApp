package com.dan.marketapp.data.order

import com.dan.marketapp.data.Address
import com.dan.marketapp.data.CartProduct

data class Order(
    val orderStatus: String,
    val totalPrice: Float,
    val products: List<CartProduct>,
    val address: Address
)
