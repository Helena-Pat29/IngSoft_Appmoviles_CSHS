package com.example.vynils.network

object CacheManager {
    private val cache = mutableMapOf<String, String>()

    fun put(key: String, value: String) {
        cache[key] = value
    }

    fun get(key: String): String? {
        return cache[key]
    }
}
