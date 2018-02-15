package com.rcorp.airpokecross.entities

import com.badlogic.gdx.graphics.Texture
import com.rcorp.airpokecross.models.Vector2D

abstract class Entity {
    var currentTexture : Texture? = null
    var position : Vector2D = Vector2D()
    var size : Vector2D = Vector2D()
}