package maystruks08.gmail.com.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import maystruks08.gmail.com.domain.entity.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthPreferences @Inject constructor(context: Context) {

    companion object {
        private const val PREFS_NAME = "auth_data"
        private const val PREFS_USER = "user"
    }

    private val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)


    fun saveCurrentUser(user: User) {
        Gson().toJson(user)
        sharedPreferences.edit().apply {
            putString(PREFS_USER,  Gson().toJson(user))
            apply()
        }
    }

    fun getCurrentUser(): User? {
        return Gson().fromJson(sharedPreferences.getString(PREFS_USER, ""), User::class.java)
    }
}