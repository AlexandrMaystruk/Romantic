package maystruks08.gmail.com.romantic.ui.profile

import maystruks08.gmail.com.romantic.core.base.BasePresenter

import javax.inject.Inject


class ProfilePresenter @Inject constructor()
    : ProfileContract.Presenter, BasePresenter<ProfileContract.View>()
