package org.steamchat.test.integration.service

import org.junit.Test as test
import org.junit.Assert.*
import org.steamchat.remote.service.*
import org.steamchat.model.user.*

public class SteamChatServiceTests {
    test fun session() {
        val sa = Account()

        val s = SteamChatService()
        s.login("hekark", Const.PASSWORD)
    }
}