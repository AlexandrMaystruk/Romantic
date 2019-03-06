package maystruks08.gmail.com.romantic.core.di.selectedhike

import dagger.Module
import dagger.Provides
import maystruks08.gmail.com.romantic.ui.selectedhike.SelectedHikeContract
import maystruks08.gmail.com.romantic.ui.selectedhike.SelectedHikePresenter

@Module
class SelectedHikeModule {

    @Provides
    @SelectedHikeScope
    fun presenter(selectedHikePresenter: SelectedHikePresenter): SelectedHikeContract.Presenter = selectedHikePresenter
}