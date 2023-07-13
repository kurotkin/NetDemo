package com.kurotkin.netdemo.domain

interface Repo {
    suspend fun getAllProducts(): Any
    suspend fun getProduct(id: Int): Any
}