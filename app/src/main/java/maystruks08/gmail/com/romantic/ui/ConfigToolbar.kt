package maystruks08.gmail.com.romantic.ui

import androidx.annotation.DrawableRes

interface ConfigToolbar {

    fun enableToolbar()

    fun disableToolbar()

    fun enableOverlay()

    fun disableOverlay()

    fun setBackground(icon: Int)

    fun setNavigationIcon(@DrawableRes icon: Int)

    fun removeNavigationIcon()

    fun showOptionMenu(showMenu: Boolean)

    fun setToolbarTitle(title: String)
}