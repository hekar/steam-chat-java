package org.steamchat.model.connection

public enum class Method(val index: Int) {
    STEAM_METHOD_GET: Method(1)
    STEAM_METHOD_POST: Method(2)
    STEAM_METHOD_SSL: Method(4)
}