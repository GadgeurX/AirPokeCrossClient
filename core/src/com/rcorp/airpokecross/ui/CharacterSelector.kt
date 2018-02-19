package com.rcorp.airpokecross.ui

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.rcorp.airpokecross.AirPokeCrossGame

class CharacterSelector(game : AirPokeCrossGame) : Table() {

    private val charactersSelectors : Array<CharacterSelectorElement> = arrayOf(CharacterSelectorElement(game.skin, game), CharacterSelectorElement(game.skin, game), CharacterSelectorElement(game.skin, game), CharacterSelectorElement(game.skin, game))
    var indexSelected = -1

    init {

        charactersSelectors[0].addListener(object : ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
                indexSelected = 0
                charactersSelectors[0].setThis(true)
                charactersSelectors[1].setThis(false)
                charactersSelectors[2].setThis(false)
                charactersSelectors[3].setThis(false)
            }
        })

        charactersSelectors[1].addListener(object : ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
                indexSelected = 1
                charactersSelectors[1].setThis(true)
                charactersSelectors[0].setThis(false)
                charactersSelectors[2].setThis(false)
                charactersSelectors[3].setThis(false)
            }
        })

        charactersSelectors[2].addListener(object : ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
                indexSelected = 2
                charactersSelectors[2].setThis(true)
                charactersSelectors[1].setThis(false)
                charactersSelectors[0].setThis(false)
                charactersSelectors[3].setThis(false)
            }
        })

        charactersSelectors[3].addListener(object : ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
                indexSelected = 3
                charactersSelectors[3].setThis(true)
                charactersSelectors[1].setThis(false)
                charactersSelectors[2].setThis(false)
                charactersSelectors[0].setThis(false)
            }
        })

        this.add(charactersSelectors[0]).space(90f)
        this.add(charactersSelectors[1]).space(90f)
        this.add(charactersSelectors[2]).space(90f)
        this.add(charactersSelectors[3]).space(90f)
    }

    fun setCharacters(index0 : Int, index1 : Int, index2 : Int, index3 : Int) {
        charactersSelectors[0].setCharacter(index0)
        charactersSelectors[1].setCharacter(index1)
        charactersSelectors[2].setCharacter(index2)
        charactersSelectors[3].setCharacter(index3)
    }
}