package com.rcorp.airpokecross.screens

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.Window
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.rcorp.airpokecross.AirPokeCrossGame
import com.rcorp.airpokecross.config.*
import com.rcorp.airpokecross.entities.Background
import com.rcorp.airpokecross.ui.CharacterSelectorElement
import sfs2x.client.core.BaseEvent
import sfs2x.client.core.SFSEvent
import com.smartfoxserver.v2.entities.data.ISFSObject
import com.smartfoxserver.v2.entities.data.SFSObject
import sfs2x.client.requests.ExtensionRequest
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.rcorp.airpokecross.ui.CharacterSelector


class CreateCharacterScreen(game: AirPokeCrossGame) : BaseScreen(game) {

    private val buttonCreateCharacter = TextButton(game.localeManager.getString("CREATE_CHARACTER_SCREEN_BUTTON_CREATE_CHARACTER"), game.skin)
    private val buttonCancel = TextButton(game.localeManager.getString("CREATE_CHARACTER_SCREEN_BUTTON_CANCEL"), game.skin)
    private val window = Window(game.localeManager.getString("CREATE_CHARACTER_SCREEN_BUTTON_CREATE_CHARACTER"), game.skin)
    private val charactersSelectors : CharacterSelector = CharacterSelector(game)

    init {
        buttonCancel.y = 10f
        buttonCancel.x = ConfigGame.VIEWPORT_WIDTH - buttonCancel.width - 10
        buttonCancel.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
                game.switchScreen(SelectCharacterScreen(game))
            }
        })

        buttonCreateCharacter.y = 10f
        buttonCreateCharacter.x = ConfigGame.VIEWPORT_WIDTH / 2 - buttonCreateCharacter.width / 2
        buttonCreateCharacter.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
                if (charactersSelectors.indexSelected != -1) {

                    Gdx.input.getTextInput(object : Input.TextInputListener {
                        override fun input(text: String) {
                            Gdx.app.postRunnable {
                                val payload = SFSObject()
                                payload.putInt(ConfigSFSPacketKey.STARTER_INDEX, charactersSelectors.indexSelected)
                                payload.putUtfString(ConfigSFSPacketKey.STARTER_NICKNAME, text)
                                game.client.send(ExtensionRequest(ConfigClientRequest.USER_CREATE_CHARACTER_REQUEST, payload, game.client.lastJoinedRoom))
                                game.switchScreen(SelectCharacterScreen(game))
                            }
                        }

                        override fun canceled() {}

                    }, game.localeManager.getString("CREATE_CHARACTER_SCREEN_NICKNAME_TITLE"), "", "")
                }
            }
        })

        window.width = ConfigGame.VIEWPORT_WIDTH / 1.5f
        window.height = ConfigGame.VIEWPORT_HEIGHT / 1.5f
        window.x = ConfigGame.VIEWPORT_WIDTH / 1.5f - window.width / 1.5f - 70
        window.y = ConfigGame.VIEWPORT_HEIGHT /1.5f - window.height /1.5f - 70
        window.isMovable = false

        window.add(charactersSelectors)

        sceneManager.addGui(window)
        sceneManager.addGui(buttonCreateCharacter)
        sceneManager.addGui(buttonCancel)
        sceneManager.addEntity(Background(game.spriteManager.getSprite(ConfigSpriteKey.SELECT_CHARACTER_BACKGROUND)))

        this.addEventHandler(SFSEvent.EXTENSION_RESPONSE)
        game.client.send(ExtensionRequest(ConfigClientRequest.USER_STARTERS_LIST_REQUEST, SFSObject(), game.client.lastJoinedRoom))
        System.out.println("[INFO] Send user starter list")
    }

    override fun dispatchEvent(event: BaseEvent) {
        super.dispatchEvent(event)
        when (event.type) {
            SFSEvent.EXTENSION_RESPONSE -> {
                val cmd = event.arguments[ConfigSFSPacketKey.SFS_EXTENSION_CMD].toString()
                val params: ISFSObject = event.arguments[ConfigSFSPacketKey.SFS_EXTENSION_PARAMS] as ISFSObject
                when(cmd) {
                    ConfigServerRequest.USER_STARTERS_LIST_REQUEST -> {
                        System.out.println("[INFO] Receive characters")
                        charactersSelectors.setCharacters(params.getSFSArray(ConfigSFSPacketKey.STARTERS).getInt(0),
                                params.getSFSArray(ConfigSFSPacketKey.STARTERS).getInt(1),
                                params.getSFSArray(ConfigSFSPacketKey.STARTERS).getInt(2),
                                params.getSFSArray(ConfigSFSPacketKey.STARTERS).getInt(3))
                    }
                }
            }
        }
    }
}