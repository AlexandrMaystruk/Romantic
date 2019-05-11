package maystruks08.gmail.com.romantic.ui.hike.myhikes

import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.romantic.core.base.IPresenter
import maystruks08.gmail.com.romantic.core.base.IView


interface MyHikesContract {

    interface View : IView {

        fun showHikes(hikeList: List<Hike>)

        fun showHikeRemoved(position: Int)
    }

    interface Presenter : IPresenter<View> {

        fun onHikeClicked(hike: Hike)

        fun initFragmentByUser()

        fun onCreateHikeClicked()

        fun onLeaveFromHikeClicked(position: Int, hike: Hike)

        fun onDeleteHikeClicked(position: Int, hike: Hike)

    }
}