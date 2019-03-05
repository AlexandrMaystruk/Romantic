package maystruks08.gmail.com.romantic.ui.selectedhike.tools

import maystruks08.gmail.com.data.room.entity.ToolsTable
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.romantic.core.BasePresenter
import maystruks08.gmail.com.romantic.core.BaseView


interface ToolsContract {

    interface View : BaseView {

        fun initUI(hikeList: List<ToolsTable>)
    }

    interface Presenter : BasePresenter<View> {

        fun getToolsList()

    }
}
