package main.type.`object`

import java.io.File

object BadWordList {
    val data = ArrayList<String>()

    init {
        populateBadWords()
    }

    private fun populateBadWords() {
        val classLoader = javaClass.classLoader
        val file = File(classLoader.getResource("badwords.txt").file)

        file.forEachLine { data.add(it) }
    }

}