package org.steamchat.connection

import org.steamchat.model.user.Account
import org.steamchat.model.connection.Method

public class Connection {
    var sa: Account = null
    var method: Method = null
    var hostname = ""
    var url = ""
    var request = ""
}