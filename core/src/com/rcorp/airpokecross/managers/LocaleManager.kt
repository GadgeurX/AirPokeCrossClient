package com.rcorp.airpokecross.managers

import com.badlogic.gdx.utils.I18NBundle
import com.badlogic.gdx.Gdx
import java.util.*


class LocaleManager {
    var baseFileHandle = Gdx.files.internal("assets/locale/lang")
    var bundle = I18NBundle.createBundle(baseFileHandle, Locale("fr"))

    fun setLocale(locale:Locale) {
        bundle = I18NBundle.createBundle(baseFileHandle, locale)
    }

    fun getString(index : String, vararg param: Any) : String {
        return try {
            bundle.format(index, param)
        } catch (e:Exception) {
            index
        }
    }
}