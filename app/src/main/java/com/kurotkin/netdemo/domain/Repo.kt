package com.kurotkin.netdemo.domain

interface Repo {

    // TODO: От бизнеса
    suspend fun getAllProducts(): Any
    suspend fun getProduct(id: Int): Any
}