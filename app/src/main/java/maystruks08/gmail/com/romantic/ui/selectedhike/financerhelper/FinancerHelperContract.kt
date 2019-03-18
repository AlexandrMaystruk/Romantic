package maystruks08.gmail.com.romantic.ui.selectedhike.financerhelper

import maystruks08.gmail.com.romantic.core.base.IPresenter
import maystruks08.gmail.com.romantic.core.base.IView


interface FinancerHelperContract {

    interface View : IView {

        fun initUI()
    }

    interface Presenter : IPresenter<View> {

        fun initUI()
    }
}
