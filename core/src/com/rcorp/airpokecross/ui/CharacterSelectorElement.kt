package com.rcorp.airpokecross.ui

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.rcorp.airpokecross.AirPokeCrossGame
import com.rcorp.airpokecross.config.ConfigSpriteKey
import com.rcorp.airpokecross.entities.Character
import com.sun.org.apache.xpath.internal.operations.Bool

class CharacterSelectorElement(skin: Skin, private val game: AirPokeCrossGame) : Stack(){
    private val table : Table = Table()
    private val stackImage : Stack = Stack()
    private val characterImage : Image = Image()
    private val characterBack : Image = Image()
    private val characterName : Label = Label("", skin)
    private val characterLvl : Label = Label("", skin)
    private val backImage : Image = Image()
    private var character : Character? = null

    init {
        characterImage.setScale(1.5f)

        add(backImage)
        stackImage.add(characterBack)
        stackImage.add(characterImage)
        table.row().spaceBottom(10f)
        table.add(stackImage)
        table.row()
        table.add(characterName)
        table.row()
        table.add(characterLvl)
        table.row()
        add(table)
    }

    fun getCharacter() : Character? {
        return character
    }

    fun setCharacter(character : Character) {
        this.character = character
        characterImage.drawable = TextureRegionDrawable(TextureRegion(game.characterPortraitManager.getSprite(character.species.ordinal)))
        characterImage.setOrigin(game.characterPortraitManager.getSprite(character.species.ordinal).width / 2f, 0f)

        characterName.setText(if (character.nickname == null){game.localeManager.getString(Character.Species.values()[character.species.ordinal].toString())} else {character.nickname!!})
        characterName.setFontScale(1.3f)
        characterLvl.setFontScale(1.1f)
        if (character.level > 0)
            characterLvl.setText(game.localeManager.getString("CHARACTER_DISPLAY_LVL_SELECTOR", character.level))

        backImage.drawable = TextureRegionDrawable(TextureRegion(game.spriteManager.getSprite(ConfigSpriteKey.CREATE_CHARACTER_FRAME)))
        backImage.scaleX = 1.5f
        backImage.scaleY = 1.7f
        backImage.setOrigin(game.spriteManager.getSprite(ConfigSpriteKey.CREATE_CHARACTER_FRAME).width / 2f, 25f)
        this.color = Color.valueOf("#FFFFFF85")
    }

    fun setThis(value: Boolean) {
        if (!value) {
            this.color = Color.valueOf("#FFFFFF85")
            backImage.color = Color.valueOf("#FFFFFFFF")
        } else {
            this.color = Color.valueOf("#FFFFFFFF")
            backImage.color = Color.valueOf("#CCCCCCCC")
        }
    }

}