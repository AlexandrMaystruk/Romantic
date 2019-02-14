package maystruks08.gmail.com.domain.repository

import io.reactivex.Completable

interface LogOutRepository {

    fun logout(): Completable

}