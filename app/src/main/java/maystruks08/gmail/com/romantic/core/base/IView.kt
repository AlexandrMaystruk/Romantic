package maystruks08.gmail.com.romantic.core.base

interface IView {

    fun configToolbar()

    fun showLoading ()

    fun hideLoading ()

    fun showError (t: Throwable)

}