package com.example.core.http

interface EntityCallback<T> {
    fun onSuccess(t:T)
    fun onFailure(message: String?)
}