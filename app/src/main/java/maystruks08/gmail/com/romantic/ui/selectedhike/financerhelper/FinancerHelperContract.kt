package maystruks08.gmail.com.romantic.ui.selectedhike.financerhelper

import maystruks08.gmail.com.romantic.core.BasePresenter
import maystruks08.gmail.com.romantic.core.BaseView


interface FinancerHelperContract {

    interface View : BaseView {

        fun initUI()
    }

    interface Presenter : BasePresenter<View> {

        fun initUI()
    }
}
