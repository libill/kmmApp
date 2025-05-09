package com.libill.kmm.alog

/**
 * ALog日志打印，Android使用 ALog.i(tag, message)
 */
val ALog: IALog by lazy { ALogImpl() }

/**
 * iOS使用ALogKt.i(tag, message)
 */
fun d(tag: String, message: String) = ALog.d(tag, message)

fun v(tag: String, message: String) = ALog.v(tag, message)

fun i(tag: String, message: String) = ALog.i(tag, message)

fun w(tag: String, message: String) = ALog.w(tag, message)

fun e(tag: String, message: String) = ALog.e(tag, message)