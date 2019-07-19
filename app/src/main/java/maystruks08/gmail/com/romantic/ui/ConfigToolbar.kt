package maystruks08.gmail.com.romantic.ui

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.MenuRes

interface ConfigToolbar {

    fun enableToolbar()

    fun disableToolbar()

    fun enableOverlay()

    fun disableOverlay()

    fun enableLogoIcon()

    fun disableLogoIcon()

    fun setBackgroundColor(@ColorRes color: Int)

    fun setBackground(icon: Int)

    fun setNavigationIcon(@DrawableRes icon: Int)

    fun removeNavigationIcon()

    fun showOptionMenu(showMenu: Boolean)

    fun setOptionMenu(@MenuRes menuRes: Int)

    fun setOptionMenuClickListener(clickListener: (Int) -> Unit)

    fun setToolbarTitle(title: String)

    fun enableBottomBar()

    fun disableBottomBar()
}