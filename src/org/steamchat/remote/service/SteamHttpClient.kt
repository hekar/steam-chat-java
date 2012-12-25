package org.steamchat.remote.service

import org.steamchat.model.user.*

private class SteamHttpClient(val hostname: String, val port: Int) {
    fun get(account: Account, path: String): Unit {

    }

    fun post(account: Account, path: String, content: String): Unit {
    }
}