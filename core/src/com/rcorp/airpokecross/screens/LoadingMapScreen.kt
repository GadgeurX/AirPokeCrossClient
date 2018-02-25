package com.rcorp.airpokecross.screens

import com.rcorp.airpokecross.AirPokeCrossGame
import com.rcorp.airpokecross.config.ConfigClientRequest
import com.rcorp.airpokecross.config.ConfigSFSPacketKey
import com.rcorp.airpokecross.config.ConfigServerRequest
import com.rcorp.airpokecross.managers.MapManager
import com.smartfoxserver.v2.entities.data.ISFSObject
import com.smartfoxserver.v2.entities.data.SFSObject
import sfs2x.client.core.BaseEvent
import sfs2x.client.core.SFSEvent
import sfs2x.client.requests.ExtensionRequest

class LoadingMapScreen(game: AirPokeCrossGame): BaseScreen(game) {

    init {
        addEventHandler(SFSEvent.EXTENSION_RESPONSE)

        game.client.send(ExtensionRequest(ConfigClientRequest.GAME_MAP_CHECKSUM, SFSObject(), game.client.lastJoinedRoom))
    }

    override fun dispatchEvent(event: BaseEvent) {
        super.dispatchEvent(event)
        when (event.type) {
            SFSEvent.EXTENSION_RESPONSE -> {
                val cmd = event.arguments[ConfigSFSPacketKey.SFS_EXTENSION_CMD].toString()
                val params: ISFSObject = event.arguments[ConfigSFSPacketKey.SFS_EXTENSION_PARAMS] as ISFSObject
                when(cmd) {
                    ConfigServerRequest.GAME_MAP_CHECKSUM -> {
                        if (!MapManager.checkMap(params.getInt(ConfigSFSPacketKey.MAP_CHECKSUM)))
                            game.client.send(ExtensionRequest(ConfigClientRequest.GAME_MAP, SFSObject(), game.client.lastJoinedRoom))
                        else
                            MapManager.loadMap()
                    }
                    ConfigServerRequest.GAME_MAP -> {
                        MapManager.saveMap(params)
                        MapManager.loadMap()
                        game.switchScreen(GameScreen(game))
                    }
                }
            }
        }
    }
}