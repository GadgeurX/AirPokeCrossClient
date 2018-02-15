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
        for (entity in entities) {
            batch.draw(entity.currentTexture, entity.position.x, entity.position.y, entity.size.x, entity.size.y)
        }
    }

    fun drawGui() {
        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
    }
}