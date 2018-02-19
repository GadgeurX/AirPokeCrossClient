package com.rcorp.airpokecross.screens

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.rcorp.airpokecross.AirPokeCrossGame
import com.rcorp.airpokecross.config.ConfigGame
import com.rcorp.airpokecross.config.ConfigSpriteKey
import com.rcorp.airpokecross.entities.Background

class SelectCharacterScreen(game: AirPokeCrossGame) : BaseScreen(game) {

    private val buttonCreateCharacter = TextButton(game.localeManager.getString("SELECT_CHARACTER_SCREEN_BUTTON_CREATE_CHARACTER"), game.skin)

    init {
        buttonCreateCharacter.y = 10f
        buttonCreateCharacter.x = ConfigGame.VIEWPORT_WIDTH - buttonCreateCharacter.width - 10
        buttonCreateCharacter.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
                game.switchScreen(CreateCharacterScreen(game))
            }
        })

        this.sceneManager.addGui(buttonCreateCharacter)
        this.sceneManager.addEntity(Background(game.spriteManager.getSprite(ConfigSpriteKey.SELECT_CHARACTER_BACKGROUND)))
    }
}