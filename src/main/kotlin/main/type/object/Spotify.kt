package main.type.`object`

import com.natpryce.konfig.PropertyGroup
import com.natpryce.konfig.getValue
import com.natpryce.konfig.stringType

object Spotify : PropertyGroup() {
    val clientID by stringType
    val clientSecret by stringType
}