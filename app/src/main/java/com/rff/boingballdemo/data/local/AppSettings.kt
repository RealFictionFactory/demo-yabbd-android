package com.rff.boingballdemo.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppSettings(
    private val context: Context
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFS_FILENAME)

    val boingBallPrefs: Flow<BoingBallPrefs> = context.dataStore.data
        .map { preferences ->
            BoingBallPrefs(
                themeColorIndex =  preferences[KEY_THEME_COLOR_INDEX] ?: 1,
                altColorIndex = preferences[KEY_ALT_COLOR_INDEX] ?: 3,
                drawFrames = preferences[KEY_DRAW_FRAMES] ?: true
            )
        }

    suspend fun saveBoingBallPrefs(value: BoingBallPrefs) {
        context.dataStore.edit { preferences ->
            preferences[KEY_THEME_COLOR_INDEX] = value.themeColorIndex
            preferences[KEY_ALT_COLOR_INDEX] = value.altColorIndex
            preferences[KEY_DRAW_FRAMES] = value.drawFrames
        }
    }

    companion object {
        const val PREFS_FILENAME = "boing_ball_user_prefs"
        private val KEY_THEME_COLOR_INDEX = intPreferencesKey("theme_color_index")
        private val KEY_ALT_COLOR_INDEX = intPreferencesKey("alt_color_index")
        private val KEY_DRAW_FRAMES = booleanPreferencesKey("draw_frames")
    }
}

data class BoingBallPrefs(
    val themeColorIndex: Int,
    val altColorIndex: Int,
    val drawFrames: Boolean,
)
