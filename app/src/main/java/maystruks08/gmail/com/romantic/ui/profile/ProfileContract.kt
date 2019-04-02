package maystruks08.gmail.com.romantic.ui.profile

import maystruks08.gmail.com.romantic.core.base.IPresenter
import maystruks08.gmail.com.romantic.core.base.IView


interface ProfileContract {

    interface View : IView

    interface Presenter : IPresenter<View>


}
