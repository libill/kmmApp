package com.libill.kmm.alog

/**
 * iOS IALog接口，带actual修饰的方法为Android/iOS公共方法，不带actual修饰的方法为iOS独有(有需要的话再添加)
 */
actual interface IALog {
    actual fun v(tag: String, message: String)
    actual fun d(tag: String, message: String)
    actual fun i(tag: String, message: String)
    actual fun w(tag: String, message: String)
    actual fun e(tag: String, message: String)
}