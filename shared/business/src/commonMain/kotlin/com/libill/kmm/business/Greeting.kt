package com.libill.kmm.business

import com.libill.kmm.utils.Platform

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}