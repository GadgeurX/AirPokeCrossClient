package com.rcorp.airpokecross.entities

import com.badlogic.gdx.graphics.Texture
import com.rcorp.airpokecross.config.ConfigGame
import com.rcorp.airpokecross.models.Vector2D

class Background(texture : Texture) : Entity() {
    init {
        this.currentTexture = texture
        this.position = Vector2D(0f, 0f)
        this.size = Vector2D(ConfigGame.VIEWPORT_WIDTH, ConfigGame.VIEWPORT_HEIGHT)
    }
}