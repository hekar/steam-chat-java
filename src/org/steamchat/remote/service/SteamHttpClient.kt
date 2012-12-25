package org.steamchat.remote.service

import org.steamchat.model.user.*
import org.apache.http.impl.client.*
import org.apache.http.client.methods.*

private class SteamHttpClient(val hostname: String, val port: Int) {

    fun get(account: Account, path: String): String? {
        val client = DefaultHttpClient()
        val h = HttpGet(hostname + path)

        return client.execute(h, BasicResponseHandler())
    }

    fun post(account: Account, path: String, content: String): String? {
        val client = DefaultHttpClient()
        val h = HttpPost(hostname + path)

        return client.execute(h, BasicResponseHandler())
    }
}