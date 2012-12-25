package org.steamchat.remote.service

import org.steamchat.model.chat.*
import org.steamchat.model.user.*

class SteamChatService() {

    val http = SteamHttpClient("api.steampowered.com", 80)
    val steamCommunityHttp = SteamHttpClient("steamcommunity.com", 80)

    fun login(username: String, password: String, steamGuardCode: String = ""): Unit {
        val sb = StringBuilder()
        sb.append("client_id=3638BFB1&")
        sb.append("grant_type=password&")
        sb.append("username=%s&".format(username))
        sb.append("password=%s&".format(password))

        if (!steamGuardCode.isEmpty()) {
            sb.append("x_emailauthcode=%s&".format(steamGuardCode))
        }

        sb.append("x_webcookie=&")
        sb.append("scope=read_profile write_profile read_client write_client")

        val sa = Account()
        http.post(sa, "/ISteamOAuth2/GetTokenWithCredentials/v0001", sb.toString())
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
        val sb = StringBuilder()

        sb.append("steamid=%s&".format(friend.steamid))
        sb.append("sessionID=%s".format(sa.sessionid))

        steamCommunityHttp.post(sa, "steamcommunity.com", "/actions/AddFriendAjax")
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

        val sb = StringBuilder()

        sb.append("access_token=%s&".format(sa.accessToken))
        sb.append("umqid=%s&".format(sa.umqid))

        // TODO: Strip html
        if (msg.startsWith("/me "))
        {
            sb.append("type=emote&")
        } else {
            sb.append("type=saytext&")
        }
        sb.append("text=%s&".format(msg))
        sb.append("steamid_dst=%s".format(message.to.steamid))

        http.post(sa, "/ISteamWebUserPresenceOAuth/Message/v0001", sb.toString())
    }

    fun setChatStatus(session: Session, chatStatus: ChatStatus): Unit {
    }
}