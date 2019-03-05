package maystruks08.gmail.com.romantic.ui.selectedhike

import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.romantic.core.BasePresenter
import maystruks08.gmail.com.romantic.core.BaseView


interface SelectedHikeContract {

    interface View : BaseView {

        fun initUI(hikeList: List<Hike>)
    }

    interface Presenter : BasePresenter<View> {

    }
}
