package com.libill.kmm.mmkv

expect class PlatformMMKV(configFileName: String?, relativePath: String?) {

    fun putString(configFileName: String, key: String, value: String)

    fun putInt(configFileName: String, key: String, value: Int)

    fun putLong(configFileName: String, key: String, value: Long)

    fun putFloat(configFileName: String, key: String, value: Float)

    fun putBoolean(configFileName: String, key: String, value: Boolean)

    fun remove(configFileName: String, key: String)

    fun clear(configFileName: String)

    fun getString(configFileName: String, key: String, defValue: String): String

    fun getInt(configFileName: String, key: String, defValue: Int): Int

    fun getLong(configFileName: String, key: String, defValue: Long): Long

    fun getFloat(configFileName: String, key: String, defValue: Float): Float

    fun getBoolean(configFileName: String, key: String, defValue: Boolean): Boolean

    fun contains(configFileName: String, key: String): Boolean
}