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
    val EXPERIENCE = intPreferencesKey("experience")        // Nuevo
    val PHONE = stringPreferencesKey("phone")
    val HANDLE = stringPreferencesKey("handle")             // Nuevo
    val EMAIL = stringPreferencesKey("email")               // Nuevo
    val SKILLS = stringPreferencesKey("skills")             // Opcional
    val GITHUB = stringPreferencesKey("github")             // Opcional
    val LINKEDIN = stringPreferencesKey("linkedin")         // Opcional
}

// Modelo de datos
data class UserData(
    val name: String = "",
    val profession: String = "",
    val experience: Int = 0,         // Nuevo
    val phone: String = "",
    val handle: String = "",         // Nuevo
    val email: String = "",          // Nuevo
    val skills: String = "",         // Opcional
    val github: String = "",         // Opcional
    val linkedin: String = ""        // Opcional
)

// Clase de acceso a preferencias
class UserPreferences(private val context: Context) {

    val userFlow: Flow<UserData> = context.dataStore.data.map { prefs ->
        UserData(
            name = prefs[PreferenceKeys.NAME] ?: "",
            profession = prefs[PreferenceKeys.PROFESSION] ?: "",
            experience = prefs[PreferenceKeys.EXPERIENCE] ?: 0, // Nuevo
            phone = prefs[PreferenceKeys.PHONE] ?: "",
            handle = prefs[PreferenceKeys.HANDLE] ?: "",        // Nuevo
            email = prefs[PreferenceKeys.EMAIL] ?: "",          // Nuevo
            skills = prefs[PreferenceKeys.SKILLS] ?: "",
            github = prefs[PreferenceKeys.GITHUB] ?: "",
            linkedin = prefs[PreferenceKeys.LINKEDIN] ?: ""
        )
    }

    suspend fun saveUserData(data: UserData) {
        context.dataStore.edit { prefs ->
            prefs[PreferenceKeys.NAME] = data.name
            prefs[PreferenceKeys.PROFESSION] = data.profession
            prefs[PreferenceKeys.EXPERIENCE] = data.experience // Nuevo
            prefs[PreferenceKeys.PHONE] = data.phone
            prefs[PreferenceKeys.HANDLE] = data.handle         // Nuevo
            prefs[PreferenceKeys.EMAIL] = data.email           // Nuevo
            prefs[PreferenceKeys.SKILLS] = data.skills
            prefs[PreferenceKeys.GITHUB] = data.github
            prefs[PreferenceKeys.LINKEDIN] = data.linkedin
        }
    }
}
