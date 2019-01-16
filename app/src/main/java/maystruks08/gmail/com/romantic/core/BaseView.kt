package maystruks08.gmail.com.romantic.core

interface BaseView {

    fun showLoading ()

    fun hideLoading ()

    fun showError (t: Throwable)

}