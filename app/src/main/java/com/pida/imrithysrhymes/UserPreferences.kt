package com.pida.imrithysrhymes

import android.content.Context
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferences(private val context: Context) {
    companion object {
        val STREAK_KEY = intPreferencesKey("streak")
    }

    // Ambil streak
    val streak: Flow<Int> = context.dataStore.data
        .map { preferences -> preferences[STREAK_KEY] ?: 0 }

    // Simpan streak
    suspend fun saveStreak(value: Int) {
        context.dataStore.edit { prefs ->
            prefs[STREAK_KEY] = value
        }
    }
}
