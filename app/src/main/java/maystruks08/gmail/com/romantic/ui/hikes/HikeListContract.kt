package maystruks08.gmail.com.romantic.ui.hikes

import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.TypeHike
import maystruks08.gmail.com.romantic.core.BasePresenter
import maystruks08.gmail.com.romantic.core.BaseView


interface HikeListContract {

    interface View : BaseView {

        fun showHikes(hikeList: List<Hike>)
    }

    interface Presenter : BasePresenter<View> {

        fun initUI(typeHike: TypeHike?)

        fun onCreateHikeClicked()

        fun onHikeClicked(hike: Hike)
    }
}
