package maystruks08.gmail.com.romantic.ui.selectedhike.participant

import maystruks08.gmail.com.romantic.core.base.IPresenter
import maystruks08.gmail.com.romantic.core.base.IView


interface ParticipantContract {

    interface View : IView

    interface Presenter : IPresenter<View> {

    }
}
