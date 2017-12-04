package main.type.api

import main.Main
import main.SpotifyAPI
import main.type.`object`.Spotify

object SpotifyAPI {
    private val clientID = Main.config[Spotify.clientID]
    private val clientSecret = Main.config[Spotify.clientSecret]

    private val api = SpotifyAPI.Builder(clientID, clientSecret).build()

    fun determineExplicity(songName: String): Boolean {
        val searchResult = api.search.searchTrack(songName)
        return searchResult.items.first().explicit
    }
}