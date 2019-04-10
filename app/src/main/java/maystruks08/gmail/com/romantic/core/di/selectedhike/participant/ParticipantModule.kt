package maystruks08.gmail.com.romantic.core.di.selectedhike.participant

import dagger.Binds
import dagger.Module
import dagger.Provides
import maystruks08.gmail.com.data.mappers.UserMapper
import maystruks08.gmail.com.data.repository.ParticipantRepositoryImpl
import maystruks08.gmail.com.domain.interactor.participant.ParticipantInteractor
import maystruks08.gmail.com.domain.interactor.participant.ParticipantInteractorImpl
import maystruks08.gmail.com.domain.repository.ParticipantRepository
import maystruks08.gmail.com.romantic.core.di.launcher.LauncherScope
import maystruks08.gmail.com.romantic.ui.selectedhike.participant.ParticipantContract
import maystruks08.gmail.com.romantic.ui.selectedhike.participant.ParticipantPresenter

@Module
abstract class ParticipantModule {


    @Binds
    @ParticipantScope
    abstract fun presenter(participantPresenter: ParticipantPresenter): ParticipantContract.Presenter

    @Binds
    @ParticipantScope
    abstract fun interactor(participantInteractorImpl: ParticipantInteractorImpl): ParticipantInteractor

    @Binds
    @ParticipantScope
    abstract fun repository(userRepositoryImpl: ParticipantRepositoryImpl): ParticipantRepository

}