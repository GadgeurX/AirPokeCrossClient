package com.rcorp.airpokecross.managers

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.files.FileHandle
import com.rcorp.airpokecross.config.ConfigMusicKey

class MusicManager {


    private val musics = mutableMapOf<String, FileHandle>()
    private var currentMusic : Music? = null

    init {
        musics[ConfigMusicKey.LOGIN_SCREEN_MUSIC] = Gdx.files.internal ("assets/sound/title_screen.mp3")
    }

    fun playMusic(index: String) {
        if (currentMusic != null) {
            currentMusic!!.stop()
            currentMusic!!.dispose()
        }
        currentMusic = try {
            Gdx.audio.newMusic(musics[index])
        } catch (e : Exception) {
            System.out.println("[ERROR] Cannot load music file " + index)
            null
        }
        if (currentMusic != null) {
            currentMusic!!.isLooping = true
            currentMusic!!.play()
        }
    }
}