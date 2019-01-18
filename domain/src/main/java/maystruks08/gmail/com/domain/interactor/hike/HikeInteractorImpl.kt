package maystruks08.gmail.com.domain.interactor.hike

import maystruks08.gmail.com.domain.repository.HikesRepository
import javax.inject.Inject

class HikeInteractorImpl @Inject constructor(val repository: HikesRepository) : HikeInteractor {
}