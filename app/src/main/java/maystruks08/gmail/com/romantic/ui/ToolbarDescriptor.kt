package maystruks08.gmail.com.romantic.ui

data class ToolbarDescriptor(
    val visible: Boolean,
    val title: String? = null,
    val navigationIcon: Int? = null,
    val collapse: Boolean = false
)
