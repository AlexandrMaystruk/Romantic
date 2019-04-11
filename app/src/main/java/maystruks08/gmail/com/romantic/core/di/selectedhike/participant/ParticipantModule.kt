package maystruks08.gmail.com.romantic.core.di.selectedhike.participant

import dagger.Module
import dagger.Provides
import maystruks08.gmail.com.data.repository.ParticipantRepositoryImpl

import maystruks08.gmail.com.domain.interactor.participant.ParticipantInteractor
import maystruks08.gmail.com.domain.interactor.participant.ParticipantInteractorImpl
import maystruks08.gmail.com.domain.repository.ParticipantRepository
import maystruks08.gmail.com.romantic.ui.selectedhike.participant.ParticipantContract
import maystruks08.gmail.com.romantic.ui.selectedhike.participant.ParticipantPresenter
import maystruks08.gmail.com.romantic.ui.selectedhike.invitetogroup.InviteUserContract
import maystruks08.gmail.com.romantic.ui.selectedhike.invitetogroup.UserPresenter

@Module
class ParticipantModule {

    @Provides
    @ParticipantScope
    fun presenter(participantPresenter: ParticipantPresenter): ParticipantContract.Presenter = participantPresenter

    @Provides
    @ParticipantScope
    fun presenterInvite(userPresenter: UserPresenter): InviteUserContract.Presenter = userPresenter

    @Provides
    @ParticipantScope
    fun interactor(participantInteractorImpl: ParticipantInteractorImpl): ParticipantInteractor = participantInteractorImpl

    @Provides
    @ParticipantScope
    fun repository(userRepositoryImpl: ParticipantRepositoryImpl): ParticipantRepository = userRepositoryImpl

}