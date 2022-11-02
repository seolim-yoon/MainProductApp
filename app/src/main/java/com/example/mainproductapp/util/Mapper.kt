package com.example.mainproductapp.util

interface Mapper<FROM, TO> {
    fun map(from: FROM): TO
}