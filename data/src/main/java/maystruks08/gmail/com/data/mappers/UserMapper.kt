package maystruks08.gmail.com.data.mappers

import com.google.firebase.auth.AuthResult
import maystruks08.gmail.com.data.room.entity.UserTable

class UserMapper {

    fun authInfoToUserTable(authResult: AuthResult): UserTable{
        return authResult.let {
            UserTable(
                id = it.user.uid,
                displayName = it.user.displayName,
                email = it.user.email
            )
        }
    }
}