package main.type.api

import main.Main
import main.type.`object`.MusiXMatch
import org.jmusixmatch.MusixMatch

object MusiXMatchAPI {
    private val api = MusixMatch(Main.config[MusiXMatch.apiKey])

    fun search(
            q: String,
            q_artist: String = "",
            q_track: String = "",
            page: Int = 1,
            pageSize: Int = 1,
            f_has_lyrics: Boolean = false
    ): HashMap<String, String> {
        val track = api.searchTracks(q, q_artist, q_track, page, pageSize, f_has_lyrics).first()

        val data = HashMap<String, String>()
        data["name"] = track.track.trackName
        data["artist"] = track.track.artistName
        data["isExplicit"] = track.track.explicit.toString()

        if (track.track.hasLyrics != 0) {
            data["lyrics"] = api.getLyrics(track.track.trackId).lyricsBody
        }
        return data
    }

}