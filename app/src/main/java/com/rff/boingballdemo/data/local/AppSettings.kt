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

    var themeColorIndexFlow: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[KEY_THEME_COLOR_INDEX] ?: 0
        }

    var altColorIndex: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[KEY_ALT_COLOR_INDEX] ?: 0
        }

    var drawFrames: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[KEY_DRAW_FRAMES] ?: true
        }

    suspend fun saveThemeColorIndex(context: Context, index: Int) {
        context.dataStore.edit { preferences ->
            preferences[KEY_THEME_COLOR_INDEX] = index
        }
    }

    suspend fun saveAltColorIndex(context: Context, index: Int) {
        context.dataStore.edit { preferences ->
            preferences[KEY_ALT_COLOR_INDEX] = index
        }
    }

    suspend fun saveDrawFrames(context: Context, value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[KEY_DRAW_FRAMES] = value
        }
    }

    companion object {
        const val PREFS_FILENAME = "boing_ball_user_prefs"
        private val KEY_THEME_COLOR_INDEX = intPreferencesKey("theme_color_index")
        private val KEY_ALT_COLOR_INDEX = intPreferencesKey("alt_color_index")
        private val KEY_DRAW_FRAMES = booleanPreferencesKey("draw_frames")
    }
}
