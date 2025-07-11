package com.example.businesscard.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Nombre del archivo de DataStore
private const val PREFS_NAME = "user_prefs"

// Extensión de contexto para acceder al DataStore
val Context.dataStore by preferencesDataStore(name = PREFS_NAME)

// Claves para los campos
object PreferenceKeys {
    val NAME = stringPreferencesKey("name")
    val PROFESSION = stringPreferencesKey("profession")
    val PHONE = stringPreferencesKey("phone")
    val SKILLS = stringPreferencesKey("skills")
    val GITHUB = stringPreferencesKey("github")
    val LINKEDIN = stringPreferencesKey("linkedin")
}

// Modelo de datos
data class UserData(
    val name: String,
    val profession: String,
    val phone: String,
    val skills: String,
    val github: String,
    val linkedin: String
)

// Clase de acceso a preferencias
class UserPreferences(private val context: Context) {

    // Flujo reactivo de datos
    val userFlow: Flow<UserData> = context.dataStore.data.map { prefs ->
        UserData(
            name = prefs[PreferenceKeys.NAME] ?: "",
            profession = prefs[PreferenceKeys.PROFESSION] ?: "",
            phone = prefs[PreferenceKeys.PHONE] ?: "",
            skills = prefs[PreferenceKeys.SKILLS] ?: "",
            github = prefs[PreferenceKeys.GITHUB] ?: "",
            linkedin = prefs[PreferenceKeys.LINKEDIN] ?: ""
        )
    }

    // Guardar todos los campos
    suspend fun saveUserData(data: UserData) {
        context.dataStore.edit { prefs ->
            prefs[PreferenceKeys.NAME] = data.name
            prefs[PreferenceKeys.PROFESSION] = data.profession
            prefs[PreferenceKeys.PHONE] = data.phone
            prefs[PreferenceKeys.SKILLS] = data.skills
            prefs[PreferenceKeys.GITHUB] = data.github
            prefs[PreferenceKeys.LINKEDIN] = data.linkedin
        }
    }
}
