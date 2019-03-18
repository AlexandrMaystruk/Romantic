package maystruks08.gmail.com.romantic.core.base

import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivityPresenter<T : IActivityView> :
    IActivityPresenter<T> {

    override var view: T? = null
    override val compositeDisposable = CompositeDisposable()

    override fun bindView(view: T) {
        this.view = view
    }

    override fun end() {
        view = null
        compositeDisposable.dispose()
        compositeDisposable.clear()
    }

}