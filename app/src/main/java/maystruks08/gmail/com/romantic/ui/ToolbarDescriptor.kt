package maystruks08.gmail.com.romantic.ui


class ToolbarDescriptor private constructor(
    val visible: Boolean = false,
    val title: String? = null,
    val logoIcon: Boolean = false,
    val navigationIcon: Int? = null,
    val collapse: Boolean = false,
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

        fun menu(menu: Int?) = apply {
            this.menu = menu
        }

        fun build() =
            ToolbarDescriptor(visible, title, logoIcon, navigationIcon, collapse, bottomBarVisibility, menu)
    }

}