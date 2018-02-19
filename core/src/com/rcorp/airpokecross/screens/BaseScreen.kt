package com.rcorp.airpokecross.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.scenes.scene2d.Stage
import com.rcorp.airpokecross.AirPokeCrossGame
import com.rcorp.airpokecross.config.ConfigGame
import com.rcorp.airpokecross.managers.SceneManager
import com.rcorp.airpokecross.utils.ScreenUtils
import sfs2x.client.core.BaseEvent
import sfs2x.client.core.IEventListener

abstract class BaseScreen(val game: AirPokeCrossGame) : Screen, IEventListener {
    val camera: OrthographicCamera = OrthographicCamera(ConfigGame.VIEWPORT_WIDTH, ConfigGame.VIEWPORT_HEIGHT)
    val sceneManager = SceneManager(Stage())
    val eventList: MutableList<String> = mutableListOf()

    init {
        camera.setToOrtho(false, ConfigGame.VIEWPORT_WIDTH, ConfigGame.VIEWPORT_HEIGHT)
    }

    override fun hide() {

    }

    override fun show() {
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        camera.update()
        game.batch.projectionMatrix = camera.combined

        game.batch.begin()
        sceneManager.draw(game.batch)
        game.batch.end()

        sceneManager.drawGui()
    }

    override fun dispatch(event: BaseEvent?) {
        if (event != null)
        Gdx.app.postRunnable { dispatchEvent(event) }
    }

    open fun dispatchEvent(event: BaseEvent) {

    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun resize(width: Int, height: Int) {
        ScreenUtils.resize(camera, sceneManager.stage, width, height)
    }

    fun addEventHandler(eventType: String) {
        game.client.addEventListener(eventType, this)
        eventList.add(eventType)
    }

    override fun dispose() {
        for (event in eventList) {
            game.client.removeEventListener(event, this)
        }
    }
}