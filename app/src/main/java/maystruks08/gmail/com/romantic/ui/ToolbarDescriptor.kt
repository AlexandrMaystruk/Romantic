package maystruks08.gmail.com.romantic.ui

import maystruks08.gmail.com.romantic.R


class ToolbarDescriptor private constructor(
    val visible: Boolean = false,
    val title: String? = null,
    val logoIcon: Boolean = false,
    val navigationIcon: Int? = null,
    val collapse: Boolean = false,
    val color: Int? = null,
    val bottomBarVisibility: Boolean = true,
    val menu: Int? = null
) {

    class Builder(
        private var visible: Boolean = true,
        private var title: String? = null,
        private var logoIcon: Boolean = true,
        private var navigationIcon: Int? = null,
        private var collapse: Boolean = false,
        private var bottomBarVisibility: Boolean = true,
        private var color: Int = R.color.colorPrimary,
        private var menu: Int? = null
    ) {

        fun visibility(visible: Boolean) = apply {
            this.visible = visible
        }

        fun title(title: String?) = apply {
            this.title = title
        }

        fun logoIcon(logoIcon: Boolean) = apply {
            this.logoIcon = logoIcon
        }

        fun navigationIcon(navigationIcon: Int?) = apply {
            this.navigationIcon = navigationIcon
        }

        fun collapse(collapse: Boolean) = apply {
            this.collapse = collapse
        }

        fun bottomBarVisibility(bottomBarVisibility: Boolean) = apply {
            this.bottomBarVisibility = bottomBarVisibility
        }

        fun color(color: Int) = apply {
            this.color = color
        }

        fun menu(menu: Int?) = apply {
            this.menu = menu
        }

        fun build() =
            ToolbarDescriptor(visible, title, logoIcon, navigationIcon, collapse, color, bottomBarVisibility, menu)
    }

}