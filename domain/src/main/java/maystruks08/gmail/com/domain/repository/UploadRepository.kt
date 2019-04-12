package maystruks08.gmail.com.domain.repository

import io.reactivex.Single

interface UploadRepository {

    fun uploadNotUploadedHikes(): Single<Int>

    fun uploadNotUploadedParticipants(): Single<Int>

    fun updateHike(): Single<Int>

    fun updateParticipants(): Single<Int>
}