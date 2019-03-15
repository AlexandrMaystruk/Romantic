package maystruks08.gmail.com.romantic.ui.event

import maystruks08.gmail.com.romantic.core.BasePresenter
import maystruks08.gmail.com.romantic.core.BaseView


interface EventContract {

    interface View : BaseView {

    }

    interface Presenter : BasePresenter<View> {

    }
}
