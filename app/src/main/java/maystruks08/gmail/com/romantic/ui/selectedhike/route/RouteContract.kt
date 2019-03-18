package maystruks08.gmail.com.romantic.ui.selectedhike.route

import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.romantic.core.base.IPresenter
import maystruks08.gmail.com.romantic.core.base.IView


interface RouteContract {

    interface View : IView {

        fun initUI(hikeList: List<Hike>)
    }

    interface Presenter : IPresenter<View> {

        fun getRout()

    }
}
