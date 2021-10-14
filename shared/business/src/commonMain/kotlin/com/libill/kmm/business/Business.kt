package com.libill.kmm.business

import com.libill.kmm.alog.ALog
import com.libill.kmm.mmkv.PlatformMMKV

private val mmkv = PlatformMMKV(configFileName = null, relativePath = null)

/**
 * App输入参数，并打印
 */
fun setUser(username: String) {
    ALog.i(TAG, "setUser username:$username")
    mmkv.putString("User", "username", username)
}

/**
 * 为App提供相应的参数
 */
fun getUser(): String {
    val name = mmkv.getString("User", "username", "Kotlin/Native")
    ALog.i(TAG, "getUser username:$name")
    return name
}