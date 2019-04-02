package maystruks08.gmail.com.romantic.ui.myhikes

import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.romantic.core.base.IPresenter
import maystruks08.gmail.com.romantic.core.base.IView


interface MyHikesContract {

    interface View : IView {

        fun showHikes(hikeList: List<Hike>)
    }

    interface Presenter : IPresenter<View> {

        fun onHikeClicked(hike: Hike)

        fun initFragmentByUser()

        fun onCreateHikeClicked()

    }
}