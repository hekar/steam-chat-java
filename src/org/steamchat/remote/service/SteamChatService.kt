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

        /*
              if(json_object_has_memrber(obj, "access_token"))
        {
                purple_account_set_string(sa->account, "access_token", json_object_get_string_member(obj, "access_token"));
                steam_login_with_access_token(sa);
        } else
        {
                const gchar *x_errorcode = json_object_get_string_member(obj, "x_errorcode");
                const gchar *error_description = json_object_get_string_member(obj, "error_description");
                if (g_str_equal(x_errorcode, "steamguard_code_required"))
                {
                        purple_request_input(NULL, NULL, _("Set your Steam Guard Code"),
                                                _("Copy the Steam Guard Code you will have received in your email"), NULL,
                                                FALSE, FALSE, "Steam Guard Code", _("OK"),
                                                G_CALLBACK(steam_set_steam_guard_token_cb), _("Cancel"),
                                                NULL, sa->account, NULL, NULL, sa->account);
                        purple_connection_error(sa->pc, PURPLE_CONNECTION_ERROR_AUTHENTICATION_FAILED, error_description);
                } else if (g_str_equal(x_errorcode, "incorrect_login"))
                {
                        purple_connection_error(sa->pc, PURPLE_CONNECTION_ERROR_AUTHENTICATION_FAILED, error_description);
                } else {
                        purple_connection_error(sa->pc, PURPLE_CONNECTION_ERROR_NETWORK_ERROR, error_description);
                }
        }
        */
    }

    fun newSession(account: Account): Session {
        val steamLogin = account.steamid + "||oauth:" + account.accessToken

        account.cookies.put("forceMobile", "1")
        account.cookies.put("mobileClient", "ios")
        account.cookies.put("mobileClientVersion", "1291812")
        account.cookies.put("Steam_Language", "english")
        account.cookies.put("steamLogin", steamLogin)

        val response = steamCommunityHttp.get(account, "/mobilesettings/GetManifest/v0001")

        // TODO: Get sessionID from cookies
        val sessionId = ""

        return Session(sessionId, account)
    }

    fun addFriend(session: Session, friend: Account): Unit {
        val params = mapOf(
                "steamid" to friend.steamid,
                "sessionID" to session.sessionId
        )

        steamCommunityHttp.post(session.steamAccount, "/actions/AddFriendAjax", params)
    }

    fun removeFriend(session: Session, friend: Account): Unit {
        val params = mapOf(
                "steamid" to friend.steamid,
                "sessionID" to session.sessionId
        )

        steamCommunityHttp.post(session.steamAccount, "/actions/RemoveFriendAjax", params)
    }

    fun inviteUser(session: Session, other: Account): Unit {
        val params = mapOf(
                "json" to "1",
                "xml" to "1",
                "action" to "approvePending",
                "itype" to "friend",
                "perform" to "action",
                "sessionID" to session.sessionId,
                "id" to other.steamid
        )

        steamCommunityHttp.post(session.steamAccount, "/profiles/%s/home_process", params)
    }

    fun listFriends(session: Session): Unit {
        val url = "/ISteamUserOAuth/GetFriendList/v0001?"

        val params = mapOf(
                "access_token" to session.steamAccount.accessToken,
                "steamid"  to session.steamAccount.steamid,
                "relationship" to "friend,requestrecipient"
        )

        val response = http.get(session.steamAccount, url, params)

        // TODO: Parse response
        /*
            JsonArray *friends = json_object_get_array_member(obj, "friends");
            PurpleGroup *group = NULL;
            gchar *users_to_fetch = g_strdup(""), *temp;
            guint index;

            for(index = 0; index < json_array_get_length(friends); index++)
            {
                    JsonObject *friend = json_array_get_object_element(friends, index);
                    const gchar *steamid = json_object_get_string_member(friend, "steamid");
                    const gchar *relationship = json_object_get_string_member(friend, "relationship");
                    if (g_str_equal(relationship, "friend"))
                    {
                            if (!purple_find_buddy(sa->account, steamid))
                            {
                                    if (!group)
                                    {
                                            group = purple_find_group("Steam");
                                            if (!group)
                                            {
                                                    group = purple_group_new("Steam");
                                                    purple_blist_add_group(group, NULL);
                                            }
                                    }
                                    purple_blist_add_buddy(purple_buddy_new(sa->account, steamid, NULL), NULL, group, NULL);
                            }
                            temp = users_to_fetch;
                            users_to_fetch = g_strconcat(users_to_fetch, ",", steamid, NULL);
                            g_free(temp);
                    } else if (g_str_equal(relationship, "requestrecipient"))
                    {
                            purple_account_request_authorization(
                                            sa->account, steamid, NULL,
                                            NULL, NULL, TRUE,
                                            steam_auth_accept_cb, steam_auth_reject_cb, purple_buddy_new(sa->account, steamid, NULL));
                    }
            }

            if (users_to_fetch && *users_to_fetch)
            {
                    steam_get_friend_summaries(sa, users_to_fetch);
            }
            g_free(users_to_fetch);
        */
    }

    fun setStatus(session: Session, status: Status): Unit {

        val url = "/ISteamWebUserPresenceOAuth/Message/v0001"
        val statusId = status.ordinal()

        val steamAccount = session.steamAccount
        val params = mapOf(
                "access_token" to steamAccount.accessToken,
                "umqid" to steamAccount.umqid,
                "type" to "personastate",
                "persona_state" to statusId
        )

        steamCommunityHttp.post(steamAccount, url, params)
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