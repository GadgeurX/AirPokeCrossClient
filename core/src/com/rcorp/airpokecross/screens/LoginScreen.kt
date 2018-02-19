package com.rcorp.airpokecross.screens

import com.badlogic.gdx.graphics.Color
import com.rcorp.airpokecross.AirPokeCrossGame
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.rcorp.airpokecross.config.ConfigSpriteKey
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.rcorp.airpokecross.config.ConfigGame
import com.rcorp.airpokecross.config.ConfigMusicKey
import com.rcorp.airpokecross.config.ConfigSFSPacketKey
import com.rcorp.airpokecross.entities.Background
import sfs2x.client.core.BaseEvent
import sfs2x.client.core.SFSEvent
import sfs2x.client.entities.Room
import sfs2x.client.requests.LoginRequest


class LoginScreen(game: AirPokeCrossGame) : BaseScreen(game) {

    private val button = TextButton(game.localeManager.getString("LOGIN_SCREEN_BUTTON_CONNECT"), game.skin)
    private val pwdEditText = TextField("", game.skin)
    private val loginEditText = TextField("", game.skin)
    private val labelInfo = Label("", game.skin)

    init {
        game.musicManager.playMusic(ConfigMusicKey.LOGIN_SCREEN_MUSIC)
        val stack = Table()

        button.width = 200f
        button.height = 50f

        pwdEditText.width = 150f
        pwdEditText.height = 40f
        pwdEditText.setTextFieldListener { _, key ->
            if ((key == '\r' || key == '\n')){
                startConnection()
            }
        }

        loginEditText.width = 150f
        loginEditText.height = 40f

        labelInfo.width = 150f
        labelInfo.height = 40f
        labelInfo.color = Color.RED

        stack.row().spaceBottom(40f)
        stack.add(Label(game.localeManager.getString("LOGIN_SCREEN_LOGIN_EDIT"), game.skin))
        stack.add(loginEditText)
        stack.row().spaceBottom(40f)
        stack.add(Label(game.localeManager.getString("LOGIN_SCREEN_PWD_EDIT"), game.skin))
        stack.add(pwdEditText)
        stack.row().spaceBottom(40f)
        stack.add(button).colspan(2)
        stack.row()
        stack.add(labelInfo).colspan(2)

        val window = Window(game.localeManager.getString("LOGIN_SCREEN_WINDOW_TITLE"), game.skin)
        window.x = 450f
        window.y = 300f
        window.height = 650f
        window.width = 400f

        window.add(stack)

        button.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
                startConnection()
            }
        })
        this.sceneManager.addGui(window)
        sceneManager.addEntity(Background(game.spriteManager.getSprite(ConfigSpriteKey.LOGIN_SCREEN_BACKGROUND)))

        addEventHandler(SFSEvent.LOGIN)
        addEventHandler(SFSEvent.CONNECTION)
        addEventHandler(SFSEvent.LOGIN_ERROR)
        addEventHandler(SFSEvent.ROOM_JOIN)
    }

    fun startConnection() {
        if (!game.client.isConnected) {
            System.out.println("[INFO] connecting .....")
            game.client.connect(ConfigGame.SERVER_IP, ConfigGame.SERVER_PORT)
        }
    }

    override fun dispatchEvent(event: BaseEvent) {
        when (event.type) {
            SFSEvent.LOGIN -> {
                System.out.println("[INFO] Login success")
            }
            SFSEvent.LOGIN_ERROR -> {
                System.out.println("[ERROR] Login error")
                labelInfo.setText(game.localeManager.getString("LOGIN_SCREEN_LOGIN_ERROR"))
                game.client.disconnect()
            }
            SFSEvent.ROOM_JOIN -> {
                System.out.println("[INFO] Success to join room " + (event.arguments[ConfigSFSPacketKey.SFS_ROOM_JOIN_ROOM] as Room).name)
                game.switchScreen(SelectCharacterScreen(game))
            }
            SFSEvent.CONNECTION -> {
                System.out.println("[INFO] Connection success")
                game.client.send(LoginRequest(loginEditText.text, pwdEditText.text, "AirPokeCross"))
                System.out.println("[INFO] login .....")
            }
        }
    }
}