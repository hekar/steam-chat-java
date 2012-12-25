package org.steamchat.model.user

import java.util.*

public class Account {
    var dns_queries = ArrayList<String>()
    var cookies = HashMap<String, String>()
    var hostname_ip_cache = HashMap<String, String>()
    var sent_messages_hash = HashMap<String, String>()
    var poll_timeout = 0
    var umqid = ""
    var message = ""
    var steamid = ""
    var sessionid = ""
    var idletime = 0
    var last_message_timestamp = 0

    var accessToken: String = ""
}