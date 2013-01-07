package org.steamchat.remote.service

import org.steamchat.model.user.*
import org.apache.http.impl.client.*
import org.apache.http.client.methods.*
import kotlin.nullable.fold

private class SteamHttpClient(val hostname: String, val port: Int) {

    fun get(account: Account, path: String): String? {

        val url = "http://$hostname$path"
        val client = DefaultHttpClient()
        val h = HttpGet(url)

        return client.execute(h, BasicResponseHandler())
    }

    fun get(account: Account, path: String, params: Map<String, Any>): String? {

        val url = "http://$hostname$path"
        val client = DefaultHttpClient()
        val h = HttpGet(url)

        for((k, v) in params) {
            h.getParams()?.setParameter(k, v)
        }

        return client.execute(h, BasicResponseHandler())
    }


    fun post(account: Account, path: String, params: Map<String, Any>): String? {

        val url = "http://$hostname$path"
        val client = DefaultHttpClient()
        val h = HttpPost(url)

        for((k, v) in params) {
            h.getParams()?.setParameter(k, v)
        }

        return client.execute(h, BasicResponseHandler())
    }
}