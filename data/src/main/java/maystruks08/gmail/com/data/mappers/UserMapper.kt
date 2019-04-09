package maystruks08.gmail.com.data.mappers

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import maystruks08.gmail.com.data.room.entity.UserTable
import maystruks08.gmail.com.domain.entity.User

class UserMapper {

    fun authInfoToUserTable(authResult: AuthResult): UserTable{
        return authResult.let {
            UserTable(
                id = it.user.uid,
                displayName = it.user.displayName?:"",
                email = it.user.email?:""
            )
        }
    }


    fun authToUserTable(user: User): UserTable{
        return user.let {
            UserTable(
                id = it.id,
                displayName = it.displayName,
                email = it.email,
                userExperienceMountain = it.userExperienceMountain,
                userExperienceWalking = it.userExperienceWalking,
                userExperienceSki = it.userExperienceSki,
                userExperienceWater = it.userExperienceWater,
                userPhotoUrl = it.userPhotoUrl
            )
        }
    }


    fun userTableToUser(user: UserTable): User{
        return user.let {
            User(
                id = it.id,
                displayName = it.displayName,
                email = it.email,
                userExperienceMountain = it.userExperienceMountain,
                userExperienceWalking = it.userExperienceWalking,
                userExperienceSki = it.userExperienceSki,
                userExperienceWater = it.userExperienceWater,
                userPhotoUrl = it.userPhotoUrl
            )
        }
    }

    fun fireBaseUserToUser(fireBaseUser: FirebaseUser): User{
        return fireBaseUser.let {
                User(
                    id = it.uid,
                    displayName =  it.displayName?:"",
                    email = it.email?:""

                )
        }
    }
}