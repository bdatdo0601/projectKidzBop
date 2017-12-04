package main

import com.natpryce.konfig.ConfigurationProperties
import com.natpryce.konfig.ConfigurationProperties.Companion.systemProperties
import com.natpryce.konfig.EnvironmentVariables
import com.natpryce.konfig.overriding
import main.type.Song
import java.util.*

object Main {
    val config = systemProperties() overriding
            EnvironmentVariables() overriding
            ConfigurationProperties.fromResource("defaults.properties")

    @JvmStatic
    fun main(args: Array<String>) {
        val input = Scanner(System.`in`)
        println("Please input song name: ")
        val song = Song(input.nextLine())
        println("OG Lyrics: \n${song.lyrics} \n\nExplicit: ${song.isExplicit} \n\nUpdated Lyrics: \n${song.getUpdatedLyrics()} \n\nBad word detected = ${song.existBadWords}")
    }
}