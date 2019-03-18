package maystruks08.gmail.com.romantic.ui.hike

import maystruks08.gmail.com.romantic.core.base.IPresenter
import maystruks08.gmail.com.romantic.core.base.IView


interface HikeContract {

    interface View : IView

    interface Presenter : IPresenter<View>
}
