package maystruks08.gmail.com.domain.entity

data class Participant(val user: User, val post: UserPost? = null) {
    constructor() : this(User())
}