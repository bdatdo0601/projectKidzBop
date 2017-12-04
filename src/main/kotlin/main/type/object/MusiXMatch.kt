package main.type.`object`

import com.natpryce.konfig.PropertyGroup
import com.natpryce.konfig.getValue
import com.natpryce.konfig.stringType

object MusiXMatch : PropertyGroup() {
    val apiKey by stringType
}