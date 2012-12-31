package org.steamchat.remote.service

import org.steamchat.model.chat.*
import org.steamchat.model.user.*

class SteamChatService() {

    val http = SteamHttpClient("api.steampowered.com", 80)
    val steamCommunityHttp = SteamHttpClient("steamcommunity.com", 80)

    fun login(username: String, password: String, steamGuardCode: String = ""): Unit {
        val params = mapOf(
                "client_id" to "3638BFB1",
                "grant_type" to "password",
                "username" to username,
                "password" to password,
                "x_webcookie" to "",
                "scope" to "read_profile write_profile read_client write_client"
        )

        if (!steamGuardCode.isEmpty()) {
            // TODO:
            //sb.append("x_emailauthcode=%s&".format(steamGuardCode))
        }

        val sa = Account()
        val response = http.post(sa, "/ISteamOAuth2/GetTokenWithCredentials/v0001", params)

        println(response)
    }
    
    fun newSession(account: Account): Session {
        val steamLogin = account.steamid + "||oauth:" + account.accessToken

        account.cookies.put("forceMobile","1")
        account.cookies.put("mobileClient","ios")
        account.cookies.put("mobileClientVersion","1291812")
        account.cookies.put("Steam_Language","english")
        account.cookies.put("steamLogin", steamLogin)

        steamCommunityHttp.get(account, "/mobilesettings/GetManifest/v0001")

        return Session(account)
    }

    fun addFriend(session: Session, friend: Account): Unit {
        val sa = session.steamAccount

        val params = mapOf(
                "steamid" to friend.steamid,
                "sessionID" to sa.sessionid
        )

        steamCommunityHttp.post(sa, "/actions/AddFriendAjax", params)
    }

    fun removeFriend(session: Session, friend: Account): Unit {

    }

    fun inviteUser(session: Session, other: Account): Unit {

    }

    fun listFriends(session: Session): Unit {

    }

    fun setStatus(session: Session, status: Status): Unit {
    }

    fun sendIm(session: Session, message: InstantMessage): Unit {
        val sa = session.steamAccount
        val msg = message.content

        val params = mapOf(
                "access_token" to sa.accessToken,
                "umqid" to sa.umqid,
                "type" to if (msg.startsWith("/me ")) "emote" else "saytext",
                "text" to msg,
                "steamid_dst" to message.to.steamid
        )

        http.post(sa, "/ISteamWebUserPresenceOAuth/Message/v0001", params)
    }

    fun setChatStatus(session: Session, chatStatus: ChatStatus): Unit {
    }
}