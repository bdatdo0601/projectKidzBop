package main.type

import be.thomaswinters.datamuse.connection.DatamuseCaller
import be.thomaswinters.datamuse.query.DatamuseQuery
import main.type.`object`.BadWordList
import main.type.api.MusiXMatchAPI
import main.type.api.SpotifyAPI
import java.util.*


class Song(
        val keyWord: String
) {

    private val wordList = HashMap<String, ArrayList<String>>()
    private val sentenceList = HashMap<String, String>()

    private val rand = Random()

    var name: String = ""
        private set
    var artist: String = ""
        private set
    var lyrics: String = ""
        private set
    var isExplicit: Boolean = false
        private set
    val existBadWords = ArrayList<String>()

    init {
        populateSongData()
        fragmentLyrics()
        analyzeLyricsExplicity()
        mappingLyrics()
    }

    private fun populateSongData() {
        val data = MusiXMatchAPI.search(keyWord)
        name = data["name"]!!
        artist = data["artist"]!!
        lyrics = data["lyrics"]!!
        isExplicit = (SpotifyAPI.determineExplicity(this.name))
    }


    private fun fragmentLyrics() {
        lyrics.split("\n").forEach {
            if ("\\w+".toRegex().containsMatchIn(it)) {
                sentenceList[it.toLowerCase()] = ""
                "\\w+".toRegex().findAll(it).forEach { wordList[it.value.toLowerCase()] = ArrayList() }
            }
        }
    }

    private fun analyzeLyricsExplicity() {
        BadWordList.data.forEach {
            if (wordList.contains(it)) {
                isExplicit = true
                existBadWords.add(it)
            }
        }
    }

    private fun convertToCensorString(word: String): String {
        var result: String = word[0].toString()
        for (i in 1..word.length) result += "*"
        return result
    }

    private fun mappingLyrics() {
        val queryCaller = DatamuseCaller()
        wordList.forEach { key, value ->
            BadWordList.data.forEach {
                if (it == key) {
                    val soundLikeQuery = DatamuseQuery().soundsLike(key)
                    queryCaller.call(soundLikeQuery).forEach {
                        if (!BadWordList.data.contains(it.word)) value.add(it.word)
                    }

                    val rhymeQuery = DatamuseQuery().perfectlyRhymes(key)
                    queryCaller.call(rhymeQuery).forEach {
                        if (!BadWordList.data.contains(it.word)) value.add(it.word)
                    }

                    value.add(convertToCensorString(key))
                }
            }

        }
    }


    fun getUpdatedLyrics(): String {
        if (!isExplicit) return lyrics
        var result = lyrics
        wordList.forEach { ogWord, replaceWordList ->
            if (replaceWordList.size != 0)
                result = result.replace(ogWord, replaceWordList[rand.nextInt(replaceWordList.size)], ignoreCase = true)
        }

        return result
    }
}