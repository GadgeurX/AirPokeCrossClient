package com.rcorp.airpokecross.managers

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.rcorp.airpokecross.config.ConfigSpriteKey

class SpriteManager {

    private val sprites = mutableMapOf<String, Texture>()
    private val unknownSprite = Texture(Gdx.files.internal("assets/sprites/unknown.png"))

    init {
        sprites[ConfigSpriteKey.LOGIN_SCREEN_BACKGROUND] = loadSprite("assets/sprites/login_screen_background.jpg")
        sprites[ConfigSpriteKey.SELECT_CHARACTER_BACKGROUND] = loadSprite("assets/sprites/select_character_background.png")
        sprites[ConfigSpriteKey.CREATE_CHARACTER_FRAME] = loadSprite("assets/sprites/character_frame.png")
    }

    fun getSprite(index: String) : Texture {
        return if (sprites.containsKey(index))
            sprites[index]!!
        else
            unknownSprite
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