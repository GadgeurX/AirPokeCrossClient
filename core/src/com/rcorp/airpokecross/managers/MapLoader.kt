package com.rcorp.airpokecross.managers

import com.rcorp.airpokecross.config.ConfigGame
import com.rcorp.airpokecross.config.ConfigSFSPacketKey
import com.smartfoxserver.v2.entities.data.ISFSObject
import java.io.File

object MapLoader {

    var checksum : Int = 0

    var width = 0
    var height = 0
    lateinit var tileMapArray: Array<Array<Int>>
    lateinit var tilesetMapArray: Array<Array<Pair<Int, Int>>>

    fun checkMap(checksumParam: Int) : Boolean {
        checksum = checksumParam
        loadMap()
        if (width == 0 && height == 0)
            return false

        var tmpChecksum = 0
        var x = 0
        while (x < width) {
            var y = 0
            while (y < height) {
                tmpChecksum = (tmpChecksum + tileMapArray[x][y] + tilesetMapArray[x][y].first + tilesetMapArray[x][y].second) % 10000000
                y++
            }
            x++
        }
        return checksum == tmpChecksum
    }

    fun loadMap() {
        val file = File(ConfigGame.MAP_DIRECTORY + checksum.toString())
        if (!file.exists())
            return
        val bufferedReader = file.bufferedReader()
        val mapSize = bufferedReader.readLine().split(" ")
        width = mapSize[0].toInt()
        height = mapSize[1].toInt()
        tileMapArray = Array(width, { _ -> Array(height, { _ -> -1 }) })
        tilesetMapArray = Array(width, { _ -> Array(height, { _ -> Pair(-1, -1) }) })
        bufferedReader.useLines { lines ->
            lines.forEach {
                val blockLine = it.split(" ")
                val x = blockLine[0].toInt()
                val y = blockLine[1].toInt()
                val tile = blockLine[2].toInt()
                tileMapArray[x][y] = tile
                tilesetMapArray[x][y] = Pair(blockLine[3].toInt(), blockLine[4].toInt())
            }
        }
    }

    fun saveMap(obj: ISFSObject) {
        var fileContent = ""
        val mapTileset = obj.getSFSArray(ConfigSFSPacketKey.MAP_TILESET)
        val mapTile = obj.getSFSArray(ConfigSFSPacketKey.MAP_TILES)
        fileContent += obj.getInt(ConfigSFSPacketKey.MAP_WIDTH).toString() + " "
        fileContent += obj.getInt(ConfigSFSPacketKey.MAP_HEIGHT).toString() + "\n"
        var x = 0
        while (x < obj.getInt(ConfigSFSPacketKey.MAP_WIDTH)) {
            var y = 0
            while (y < obj.getInt(ConfigSFSPacketKey.MAP_HEIGHT)) {
                fileContent += x.toString() + " " + y.toString() + " " + (mapTile.getSFSArray(x).getInt(y)).toString() + " " +
                        (mapTileset.getSFSArray(x).getSFSObject(y).getInt(ConfigSFSPacketKey.MAP_GLOBAL_TILESET)).toString() + " " +
                        (mapTileset.getSFSArray(x).getSFSObject(y).getInt(ConfigSFSPacketKey.MAP_LOCAL_TILESET)).toString() + "\n"
                y++
            }
            x++
        }
        File(ConfigGame.MAP_DIRECTORY + checksum.toString()).bufferedWriter().use { out -> out.write(fileContent) }
    }

}