package com.rcorp.airpokecross

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.rcorp.airpokecross.config.ConfigGame
import com.rcorp.airpokecross.managers.LocaleManager
import com.rcorp.airpokecross.managers.SpriteManager
import com.rcorp.airpokecross.screens.LoginScreen
import com.smartfoxserver.v2.exceptions.SFSException
import sfs2x.client.SmartFox
import sfs2x.client.core.BaseEvent
import sfs2x.client.core.IEventListener
import sfs2x.client.core.SFSEvent

class AirPokeCrossGame : Game(), IEventListener {
    lateinit var batch: SpriteBatch
    lateinit var font: BitmapFont
    lateinit var spriteManager: SpriteManager
    lateinit var localeManager: LocaleManager
    lateinit var skin: Skin
    val client = SmartFox(ConfigGame.SFS_DEBUG)


    override fun create() {
        client.addEventListener(SFSEvent.CONNECTION, this)

        client.addEventListener(SFSEvent.HANDSHAKE, this)
        client.addEventListener(SFSEvent.SOCKET_ERROR, this)

        batch = SpriteBatch()
        font = BitmapFont()
        skin = Skin(Gdx.files.internal(ConfigGame.UI_SKIN_FILE))
        spriteManager = SpriteManager()
        localeManager = LocaleManager()
        this.screen = LoginScreen(this)
    }

    override fun dispose() {
        batch.dispose()
    }

    @Throws(SFSException::class)
    override fun dispatch(baseEvent: BaseEvent) {
        Gdx.app.postRunnable {
            when (baseEvent.type) {
                SFSEvent.CONNECTION_LOST -> {
                    System.out.println("[WARNING] Connection lost")
                    if (this.screen !is LoginScreen)
                        switchScreen(LoginScreen(this))
                }
            }
        }
    }

    fun switchScreen(screen : Screen) {
        this.screen.dispose()
        this.screen = screen
    }
}
