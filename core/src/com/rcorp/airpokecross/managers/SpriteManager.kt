package com.rcorp.airpokecross.managers

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.rcorp.airpokecross.config.ConfigSpriteKey

class SpriteManager {

    private val sprites = mutableMapOf<String, Texture>()
    private val unknownSprite = Texture(Gdx.files.internal("assets/sprites/unknown.png"))

    init {
        sprites[ConfigSpriteKey.LOGIN_SCREEN_BACKGROUND] = Texture(Gdx.files.internal("assets/sprites/login_screen.jpg"))
    }

    fun getSprite(index: String) : Texture {
        return if (sprites.containsKey(index))
            sprites[index]!!
        else
            unknownSprite
    }

}