package com.rcorp.airpokecross.managers

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture

class CharacterPortraitManager {

    private val unknownSprite = Texture(Gdx.files.internal("assets/sprites/unknown.png"))
    private val sprites = mutableListOf<Texture>()


    init {
        for (i in 0 until 151) {
            sprites.add(i,loadSprite("assets/sprites/characters_portrait/" + (i + 1) + ".png"))
        }
    }

    fun getSprite(index: Int) : Texture {
        return sprites[index]
    }

    private fun loadSprite(path: String): Texture {
        return try {
            Texture(Gdx.files.internal(path))
        } catch (e: Exception) {
            System.out.println("[ERROR] Cannot load sprite " + path)
            unknownSprite
        }
    }
}