package maystruks08.gmail.com.romantic.ui

import javax.inject.Inject

class ToolBarController(private val configToolbar: ConfigToolbar) {

    @Inject
    lateinit var toolbarDescriptor: ToolbarDescriptor

    fun configure() {
        if (toolbarDescriptor.visible) {
            configToolbar.enableToolbar()
        } else {
            configToolbar.disableToolbar()
        }

        if (toolbarDescriptor.collapse) {
            configToolbar.enableOverlay()
        } else {
            configToolbar.disableOverlay()
        }

        if (toolbarDescriptor.title != null) {
            configToolbar.setToolbarTitle(toolbarDescriptor.title!!)
        }

        if (toolbarDescriptor.navigationIcon != null) {
            configToolbar.setNavigationIcon(toolbarDescriptor.navigationIcon!!)
        } else {
            configToolbar.removeNavigationIcon()
        }

    }

}