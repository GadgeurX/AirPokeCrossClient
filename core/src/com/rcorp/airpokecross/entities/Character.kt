package com.rcorp.airpokecross.entities

import com.rcorp.airpokecross.config.ConfigSFSPacketKey
import com.smartfoxserver.v2.entities.data.ISFSObject

class Character(species: Int) : Entity() {

    var id: Int = 0
    var idUser : Int = 0
    var species: Species = Species.values()[species]
    var move1: Int = 165
    var move2: Int = 165
    var move3: Int = 165
    var move4: Int = 165
    var level: Int = 0
    var exp: Int = 0
    var nickname: String? = null

    constructor(obj: ISFSObject): this(0) {
        id = obj.getInt(ConfigSFSPacketKey.CHARACTER_ID)
        idUser = obj.getInt(ConfigSFSPacketKey.CHARACTER_ID)
        species = Species.values()[obj.getInt(ConfigSFSPacketKey.CHARACTER_SPECIES)]
        move1 = obj.getInt(ConfigSFSPacketKey.CHARACTER_MOVE_1)
        move2 = obj.getInt(ConfigSFSPacketKey.CHARACTER_MOVE_2)
        move3 = obj.getInt(ConfigSFSPacketKey.CHARACTER_MOVE_3)
        move4 = obj.getInt(ConfigSFSPacketKey.CHARACTER_MOVE_4)
        level = obj.getInt(ConfigSFSPacketKey.CHARACTER_LEVEL)
        exp = obj.getInt(ConfigSFSPacketKey.CHARACTER_EXP)
        nickname = obj.getUtfString(ConfigSFSPacketKey.CHARACTER_NICKNAME)
    }

    enum class Species {
        BULBASAUR, IVYSAUR, VENUSAUR, CHARMANDER, CHARMELEON, CHARIZARD, SQUIRTLE,
        WARTORTLE, BLASTOISE, CATERPIE, METAPOD, BUTTERFREE, WEEDLE, KAKUNA, BEEDRILL,
        PIDGEY, PIDGEOTTO, PIDGEOT, RATTATA, RATICATE, SPEAROW, FEAROW, EKANS, ARBOK, PIKACHU,
        RAICHU, SANDSHREW, SANDSLASH, NIDORAN_F, NIDORINA, NIDOQUEEN, NIDORAN_M,
        NIDORINO, NIDOKING, CLEFAIRY, CLEFABLE, VULPIX, NINETALES, JIGGLYPUFF, WIGGLYTUFF, ZUBAT,
        GOLBAT, ODDISH, GLOOM, VILEPLUME, PARAS, PARASECT, VENONAT, VENOMOTH, DIGLETT, DUGTRIO,
        MEOWTH, PERSIAN, PSYDUCK, GOLDUCK, MANKEY, PRIMEAPE, GROWLITHE, ARCANINE, POLIWAG, POLIWHIRL,
        POLIWRATH, ABRA, KADABRA, ALAKAZAM, MACHOP, MACHOKE, MACHAMP, BELLSPROUT, WEEPINBELL,
        VICTREEBEL, TENTACOOL, TENTACRUEL, GEODUDE, GRAVELER, GOLEM, PONYTA, RAPIDASH, SLOWPOKE,
        SLOWBRO, MAGNEMITE, MAGNETON, FARFETCH_D, DODUO, DODRIO, SEEL, DEWGONG, GRIMER, MUK,
        SHELLDER, CLOYSTER, GASTLY, HAUNTER, GENGAR, ONIX, DROWZEE, HYPNO, KRABBY, KINGLER, VOLTORB,
        ELECTRODE, EXEGGCUTE, EXEGGUTOR, CUBONE, MAROWAK, HITMONLEE, HITMONCHAN, LICKITUNG, KOFFING,
        WEEZING, RHYHORN, RHYDON, CHANSEY, TANGELA, KANGASKHAN, HORSEA, SEADRA, GOLDEEN, SEAKING,
        STARYU, STARMIE, MR_MIME, SCYTHER, JYNX, ELECTABUZZ, MAGMAR, PINSIR, TAUROS, MAGIKARP,
        GYARADOS, LAPRAS, DITTO, EEVEE, VAPOREON, JOLTEON, FLAREON, PORYGON, OMANYTE, OMASTAR, KABUTO,
        KABUTOPS, AERODACTYL, SNORLAX, ARTICUNO, ZAPDOS, MOLTRES, DRATINI, DRAGONAIR, DRAGONITE,
        MEWTWO, MEW, MISSINGNO
    }
}