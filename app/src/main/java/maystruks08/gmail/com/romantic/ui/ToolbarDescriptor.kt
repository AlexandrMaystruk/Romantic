package maystruks08.gmail.com.romantic.ui

data class ToolbarDescriptor(
    val visible: Boolean,
    val title: String? = null,
    val logoIcon: Boolean = true,
    val navigationIcon: Int? = null,
    val collapse: Boolean = false,
    val bottomBarVisibility: Boolean = true,
    val menu: Int? = null
)



