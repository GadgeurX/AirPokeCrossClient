package com.rcorp.airpokecross.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.Window
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.rcorp.airpokecross.AirPokeCrossGame
import com.rcorp.airpokecross.config.*
import com.rcorp.airpokecross.entities.Background
import com.rcorp.airpokecross.entities.Character
import com.rcorp.airpokecross.ui.CharacterSelector
import com.smartfoxserver.v2.entities.data.ISFSObject
import com.smartfoxserver.v2.entities.data.SFSObject
import sfs2x.client.core.BaseEvent
import sfs2x.client.core.SFSEvent
import sfs2x.client.requests.ExtensionRequest

class SelectCharacterScreen(game: AirPokeCrossGame) : BaseScreen(game) {

    private val buttonQuit = TextButton(game.localeManager.getString("SELECT_CHARACTER_SCREEN_BUTTON_QUIT"), game.skin)
    private val window = Window(game.localeManager.getString("SELECT_CHARACTER_SCREEN_BUTTON_SELECT_CHARACTER"), game.skin)
    private val charactersSelectors : CharacterSelector = CharacterSelector(game)
    private val buttonCreateCharacter = TextButton(game.localeManager.getString("SELECT_CHARACTER_SCREEN_BUTTON_CREATE_CHARACTER"), game.skin)

    init {
        buttonQuit.y = 10f
        buttonQuit.x = ConfigGame.VIEWPORT_WIDTH - buttonQuit.width - 10
        buttonQuit.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
                game.client.disconnect()
                Gdx.app.exit()
            }
        })

        buttonCreateCharacter.y = 10f
        buttonCreateCharacter.x = buttonQuit.x - buttonCreateCharacter.width - 10
        buttonCreateCharacter.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
                game.switchScreen(CreateCharacterScreen(game))
            }
        })

        this.sceneManager.addGui(buttonCreateCharacter)
        this.sceneManager.addEntity(Background(game.spriteManager.getSprite(ConfigSpriteKey.SELECT_CHARACTER_BACKGROUND)))

        window.width = ConfigGame.VIEWPORT_WIDTH / 1.5f
        window.height = ConfigGame.VIEWPORT_HEIGHT / 1.5f
        window.x = ConfigGame.VIEWPORT_WIDTH / 1.5f - window.width / 1.5f - 70
        window.y = ConfigGame.VIEWPORT_HEIGHT /1.5f - window.height /1.5f - 70
        window.isMovable = false

        window.add(charactersSelectors)

        sceneManager.addGui(window)
        sceneManager.addGui(buttonCreateCharacter)
        sceneManager.addGui(buttonQuit)
        sceneManager.addEntity(Background(game.spriteManager.getSprite(ConfigSpriteKey.SELECT_CHARACTER_BACKGROUND)))

        this.addEventHandler(SFSEvent.EXTENSION_RESPONSE)

        game.client.send(ExtensionRequest(ConfigClientRequest.USER_CHARACTERS_LIST_REQUEST, SFSObject(), game.client.lastJoinedRoom))
    }

    override fun dispatchEvent(event: BaseEvent) {
        super.dispatchEvent(event)
        when (event.type) {
            SFSEvent.EXTENSION_RESPONSE -> {
                val cmd = event.arguments[ConfigSFSPacketKey.SFS_EXTENSION_CMD].toString()
                val params: ISFSObject = event.arguments[ConfigSFSPacketKey.SFS_EXTENSION_PARAMS] as ISFSObject
                when(cmd) {
                    ConfigServerRequest.USER_CHARACTERS_LIST_REQUEST -> {
                        System.out.println("[INFO] Receive starters")
                        charactersSelectors.resetSelector()
                        var i = 0
                        while (i < params.getSFSArray(ConfigSFSPacketKey.CHARACTERS).size()) {
                            charactersSelectors.addCharacter(Character(params.getSFSArray(ConfigSFSPacketKey.CHARACTERS).getSFSObject(i)))
                            i++
                        }
                    }
                }
            }
        }
    }
}