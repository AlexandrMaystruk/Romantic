package maystruks08.gmail.com.data.repository

import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.TypeHike
import maystruks08.gmail.com.domain.repository.HikesRepository
import javax.inject.Inject

class HikeRepositoryImpl @Inject constructor() : HikesRepository {
    override fun provideHikes(typeHike: TypeHike?): Single<List<Hike>> {

        if(typeHike == null){
            //get all
        } else{
           //get by type
        }
        //todo remove hardcode
        return Single.just(listOf(Hike(1, TypeHike.MOUNTAIN, "23456", "", "reg", "cat")))
    }
}