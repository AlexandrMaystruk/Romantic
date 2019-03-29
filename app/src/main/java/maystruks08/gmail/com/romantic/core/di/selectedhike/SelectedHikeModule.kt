package maystruks08.gmail.com.romantic.core.di.selectedhike

import dagger.Binds
import dagger.Module
import dagger.Provides
import maystruks08.gmail.com.romantic.ui.selectedhike.SelectedHikeContract
import maystruks08.gmail.com.romantic.ui.selectedhike.SelectedHikePresenter

@Module
abstract class SelectedHikeModule {

    @Binds
    @SelectedHikeScope
    abstract fun presenter(selectedHikePresenter: SelectedHikePresenter): SelectedHikeContract.Presenter
}