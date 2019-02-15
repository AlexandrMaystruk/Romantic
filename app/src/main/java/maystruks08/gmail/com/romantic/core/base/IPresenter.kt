package maystruks08.gmail.com.romantic.core.base

import io.reactivex.disposables.CompositeDisposable

interface IPresenter <T: IView> {

    var view: T?

    val compositeDisposable: CompositeDisposable

    fun bindView(view: T)

    fun end ()

}