package maystruks08.gmail.com.romantic.ui.createhike

import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.romantic.core.BasePresenter
import maystruks08.gmail.com.romantic.core.BaseView


interface CreateNewHikeContract {

    interface View : BaseView {

        fun initUI(hikeList: List<Hike>)
    }

    interface Presenter : BasePresenter<View> {

        fun createHike(hike: Hike)
    }
}
