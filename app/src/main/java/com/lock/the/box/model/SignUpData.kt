package com.lock.the.box.model

data class SignUpData(
    val code: Int,
    val data: SignUpUserIdData,
    val message: String,
    val status: Int
)

data class SignUpUserIdData(val user_id: String)