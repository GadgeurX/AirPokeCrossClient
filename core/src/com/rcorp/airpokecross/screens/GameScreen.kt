package com.rcorp.airpokecross.screens

import com.rcorp.airpokecross.AirPokeCrossGame
import com.rcorp.airpokecross.config.ConfigSFSPacketKey
import com.rcorp.airpokecross.config.ConfigServerRequest
import com.smartfoxserver.v2.entities.data.ISFSObject
import sfs2x.client.core.BaseEvent
import sfs2x.client.core.SFSEvent

class GameScreen(game: AirPokeCrossGame): BaseScreen(game) {

    init {
    }

    override fun dispatchEvent(event: BaseEvent) {
        super.dispatchEvent(event)
        when (event.type) {
            SFSEvent.EXTENSION_RESPONSE -> {
                val cmd = event.arguments[ConfigSFSPacketKey.SFS_EXTENSION_CMD].toString()
                val params: ISFSObject = event.arguments[ConfigSFSPacketKey.SFS_EXTENSION_PARAMS] as ISFSObject
                when(cmd) {
                    ConfigServerRequest.GAME_MAP_CHECKSUM -> {

                    }
                    ConfigServerRequest.GAME_MAP -> {
                    }
                }
            }
        }
    }
}