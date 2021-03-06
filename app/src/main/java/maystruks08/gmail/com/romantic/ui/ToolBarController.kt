package maystruks08.gmail.com.romantic.ui

class ToolBarController {

    fun configure(toolbarDescriptor: ToolbarDescriptor, configToolbar: ConfigToolbar) {
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
            configToolbar.setToolbarTitle(toolbarDescriptor.title)
        }

        if (toolbarDescriptor.logoIcon) {
            configToolbar.enableLogoIcon()
        } else {
            configToolbar.disableLogoIcon()
        }

        if (toolbarDescriptor.color != null){
            configToolbar.setBackgroundColor(toolbarDescriptor.color)
        }

        if (toolbarDescriptor.navigationIcon != null) {
            configToolbar.setNavigationIcon(toolbarDescriptor.navigationIcon)
            configToolbar.disableLogoIcon()
        } else {
            configToolbar.removeNavigationIcon()
            configToolbar.enableLogoIcon()
        }

        if (toolbarDescriptor.bottomBarVisibility) {
            configToolbar.enableBottomBar()
        } else {
            configToolbar.disableBottomBar()
        }

        if (toolbarDescriptor.menu != null) {
            configToolbar.setOptionMenu(toolbarDescriptor.menu)
        }
    }

    fun addMenuClickListener(configToolbar: ConfigToolbar, menuClickListener: (Int) -> Unit){
        configToolbar.setOptionMenuClickListener(menuClickListener)
    }
}