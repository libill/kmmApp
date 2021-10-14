package com.libill.kmm.alog

/**
 * Android IALog接口，带actual修饰的方法为Android/iOS公共方法，不带actual修饰的方法为Android独有
 */
actual interface IALog {
    actual fun v(tag: String, message: String)
    actual fun d(tag: String, message: String)
    actual fun i(tag: String, message: String)
    actual fun w(tag: String, message: String)
    actual fun e(tag: String, message: String)

    fun v(tag: String, format: String, vararg args: Any?)
    fun d(tag: String, format: String, vararg args: Any?)
    fun i(tag: String, format: String, vararg args: Any?)
    fun w(tag: String, format: String, vararg args: Any?)
    fun e(tag: String, format: String, vararg args: Any?)

    fun destroy()
}