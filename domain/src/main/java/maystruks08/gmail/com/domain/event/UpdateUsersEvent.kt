package maystruks08.gmail.com.domain.event

import maystruks08.gmail.com.domain.entity.User

data class UpdateUsersEvent(val users: List<User>) : Event()