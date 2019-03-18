package maystruks08.gmail.com.romantic.ui.event

import maystruks08.gmail.com.romantic.core.base.IPresenter
import maystruks08.gmail.com.romantic.core.base.IView


interface EventContract {

    interface View : IView {

    }

    interface Presenter : IPresenter<View> {

    }
}
