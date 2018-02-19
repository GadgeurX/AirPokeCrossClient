package com.rcorp.airpokecross.managers

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.rcorp.airpokecross.entities.Entity

class SceneManager(val stage : Stage) {
    private val entities : MutableList<Entity> = mutableListOf()

    init {
        Gdx.input.inputProcessor = stage
    }

    fun addGui(actor: Actor) {
        stage.addActor(actor)
    }

    fun addEntity(entity : Entity) {
        entities.add(entity)
    }

    fun draw(batch: Batch) {
        entities
                .filter { it.currentTexture != null }
                .forEach { batch.draw(it.currentTexture, it.position.x, it.position.y, it.size.x, it.size.y) }
    }

    fun drawGui() {
        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
    }
}