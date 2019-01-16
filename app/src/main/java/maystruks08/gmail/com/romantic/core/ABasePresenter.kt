package maystruks08.gmail.com.romantic.core

import io.reactivex.disposables.CompositeDisposable

abstract class ABasePresenter<T: BaseView>: BasePresenter<T> {

    override var view: T? = null
    override val compositeDisposable = CompositeDisposable()

    override fun bindView(view: T) {
        this.view = view
    }

    fun showBaseError (t: Throwable) {
        view?.hideLoading()
        view?.showError(t)
        t.printStackTrace()
    }

    override fun end() {
        view = null
        compositeDisposable.dispose()
        compositeDisposable.clear()
    }

}