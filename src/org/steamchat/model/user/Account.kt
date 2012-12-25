package org.steamchat.model.user

public class Account {
    var dns_queries?: List<String> = null
    var cookie_table?: Map<String, String> = null
    var hostname_ip_cache?: Map<String, String> = null
    var sent_messages_hash?: Map<String, String> = null
    var  poll_timeout = 0
    var  umqid = ""
    var  message = 0
    var  steamid = ""
    var  sessionid = ""
    var idletime = 0
    var  last_message_timestamp = 0
}