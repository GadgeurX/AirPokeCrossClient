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
    private val characterName : Label = Label("ERROR", skin)
    private val backImage : Image = Image()

    init {
        characterImage.setScale(1.5f)

        add(backImage)
        stackImage.add(characterBack)
        stackImage.add(characterImage)
        table.row().spaceBottom(10f)
        table.add(stackImage)
        table.row()
        table.add(characterName)
        add(table)
    }

    fun setCharacter(id : Int) {
        characterImage.drawable = TextureRegionDrawable(TextureRegion(game.characterPortraitManager.getSprite(id)))
        characterImage.setOrigin(game.characterPortraitManager.getSprite(id).width / 2f, 0f)

        characterName.setText(game.localeManager.getString(Character.Species.values()[id].toString()))

        backImage.drawable = TextureRegionDrawable(TextureRegion(game.spriteManager.getSprite(ConfigSpriteKey.CREATE_CHARACTER_FRAME)))
        backImage.setScale(1.5f)
        backImage.setOrigin(game.spriteManager.getSprite(ConfigSpriteKey.CREATE_CHARACTER_FRAME).width / 2f, 0f)
        this.color = Color.valueOf("#FFFFFF85")
    }

    fun setThis(value: Boolean) {
        if (!value) {
            this.color = Color.valueOf("#FFFFFF85")
            backImage.color = Color.valueOf("#FFFFFFFF")
        } else {
            this.color = Color.valueOf("#FFFFFFFF")
            backImage.color = Color.valueOf("#FFFFFFBB")
        }
    }

}