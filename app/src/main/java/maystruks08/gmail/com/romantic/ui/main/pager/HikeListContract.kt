package maystruks08.gmail.com.romantic.ui.main.pager

import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.TypeHike
import maystruks08.gmail.com.romantic.core.base.IPresenter
import maystruks08.gmail.com.romantic.core.base.IView


interface HikeListContract {

    interface View : IView {

        fun showHikes(hikeList: List<Hike>, position: Int)
    }

    interface Presenter : IPresenter<View> {

        fun downloadHikeData()

        fun initFragment(typeHike: TypeHike, position: Int)

        fun onHikeClicked(hike: Hike)
    }
}