package maystruks08.gmail.com.romantic.ui.createhike

import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.romantic.core.base.IPresenter
import maystruks08.gmail.com.romantic.core.base.IView


interface CreateNewHikeContract {

    interface View : IView {

        fun initUI(hikeList: List<Hike>)
    }

    interface Presenter : IPresenter<View> {

        fun createHike(hike: Hike)
    }
}