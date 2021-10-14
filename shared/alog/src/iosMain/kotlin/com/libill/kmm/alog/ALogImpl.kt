package com.libill.kmm.alog

import cocoapods.CocoaLumberjack.*
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSUserDomainMask

/**
 * ALogImpl实现不用生成头文件，类修饰需要加上internal
 */
internal actual class ALogImpl actual constructor() : IALog {

    private val dLog = DDLog
    private val asynchronousLog = true

    init {
        val fileLogger = DDFileLogger(DDLogFileManagerDefault(this.getLogPath()))
        fileLogger.rollingFrequency = 60.0 * 60 * 24            // 24 hours
        fileLogger.logFileManager.maximumNumberOfLogFiles = 7U  // 7 days
        dLog.addLogger(fileLogger)
        dLog.addLogger(DDOSLogger().apply {
        })
    }

    private fun getLogPath(): String {
        val paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, true)
        return if (paths.isEmpty()) {
            ""
        } else {
            paths[0] as String + "/" + "alog"
        }
    }

    override fun v(tag: String, message: String) {
        dLog.log(asynchronousLog, toMessage(tag, "[$tag] $message", DDLogLevelVerbose, DDLogFlagVerbose))
    }

    override fun d(tag: String, message: String) {
        dLog.log(asynchronousLog, toMessage(tag, "[$tag] $message", DDLogLevelDebug, DDLogFlagDebug))
    }

    override fun i(tag: String, message: String) {
        dLog.log(asynchronousLog, toMessage(tag, "[$tag] $message", DDLogLevelInfo, DDLogFlagInfo))
    }

    override fun w(tag: String, message: String) {
        dLog.log(asynchronousLog, toMessage(tag, "[$tag] $message", DDLogLevelWarning, DDLogFlagWarning))
    }

    override fun e(tag: String, message: String) {
        dLog.log(asynchronousLog, toMessage(tag, "[$tag] $message", DDLogLevelError, DDLogFlagError))
    }

    private fun toMessage(tag: String, message: String, level: DDLogLevel, flag: DDLogFlag): DDLogMessage {
        return DDLogMessage(message, level, flag, 0, "", null, 0, tag, 0, null)
    }
}