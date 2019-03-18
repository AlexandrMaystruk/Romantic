package maystruks08.gmail.com.romantic.ui.selectedhike.tools

import maystruks08.gmail.com.data.room.entity.ToolsTable
import maystruks08.gmail.com.romantic.core.base.IPresenter
import maystruks08.gmail.com.romantic.core.base.IView


interface ToolsContract {

    interface View : IView {

        fun initUI(hikeList: List<ToolsTable>)
    }

    interface Presenter : IPresenter<View> {

        fun getToolsList()

    }
}
