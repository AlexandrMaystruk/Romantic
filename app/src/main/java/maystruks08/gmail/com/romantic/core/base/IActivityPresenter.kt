package maystruks08.gmail.com.romantic.core.base

import io.reactivex.disposables.CompositeDisposable

interface IActivityPresenter <T: IActivityView> {

    var view: T?

    val compositeDisposable: CompositeDisposable

    fun bindView(view: T)

    fun end ()

}