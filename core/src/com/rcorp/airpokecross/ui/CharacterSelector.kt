package com.rcorp.airpokecross.ui

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.rcorp.airpokecross.AirPokeCrossGame
import com.rcorp.airpokecross.entities.Character

class CharacterSelector(val game: AirPokeCrossGame) : Table() {

    private val charactersSelectors: MutableList<CharacterSelectorElement> = mutableListOf()
    var indexSelected = -1

    init {
    }

    fun getCharacterSelected() : Character? {
        return charactersSelectors[indexSelected].getCharacter()
    }

    fun setCharacters(vararg indexs: Int) {
        for (index in indexs) {
            val character = CharacterSelectorElement(game.skin, game)
            character.setCharacter(Character(index))
            this.add(character).space(90f)
            character.addListener(object : ClickListener() {
                override fun clicked(event: InputEvent, x: Float, y: Float) {
                    indexSelected = indexs.indexOf(index)
                    for (char in charactersSelectors) {
                        char.setThis(false)
                    }
                    character.setThis(true)
                }
            })
            charactersSelectors.add(character)
        }
    }

    fun addCharacter(character: Character) {
        val characterElem = CharacterSelectorElement(game.skin, game)
        characterElem.setCharacter(character)
        this.add(characterElem).space(90f)
        var thisIndex = charactersSelectors.size
        characterElem.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
                indexSelected = thisIndex
                for (char in charactersSelectors) {
                    char.setThis(false)
                }
                characterElem.setThis(true)
            }
        })
        charactersSelectors.add(characterElem)
    }

    fun resetSelector() {
        this.reset()
        charactersSelectors.clear()
    }
}