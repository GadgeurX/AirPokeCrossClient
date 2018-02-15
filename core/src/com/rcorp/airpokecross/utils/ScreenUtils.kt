package com.rcorp.airpokecross.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.Scaling
import com.rcorp.airpokecross.config.ConfigGame

object ScreenUtils {

    fun resize(camera: OrthographicCamera, stage : Stage, width: Int, height: Int) {
        camera.update()
        val size = Scaling.fit.apply(ConfigGame.VIEWPORT_WIDTH, ConfigGame.VIEWPORT_HEIGHT, width.toFloat(), height.toFloat())
        val viewportX = (width - size.x).toInt() / 2
        val viewportY = (height - size.y).toInt() / 2
        val viewportWidth = size.x.toInt()
        val viewportHeight = size.y.toInt()
        Gdx.gl.glViewport(viewportX, viewportY, viewportWidth, viewportHeight)
        stage.viewport.update(width, height, true)
    }
}