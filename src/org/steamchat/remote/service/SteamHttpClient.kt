package org.steamchat.remote.service

import org.steamchat.model.user.*
import org.apache.http.impl.client.*
import org.apache.http.client.methods.*

private class SteamHttpClient(val hostname: String, val port: Int) {

    fun get(account: Account, path: String): String? {
        val client = DefaultHttpClient()
        val h = HttpGet("http://" + hostname + path)

        return client.execute(h, BasicResponseHandler())
    }

    fun post(account: Account, path: String, params: Map<String, Any>): String? {
        val client = DefaultHttpClient()
        val h = HttpPost("http://" + hostname + path)

        for((k, v) in params) {
            h.getParams()?.setParameter(k, v)
        }

        return client.execute(h, BasicResponseHandler())
    }
}