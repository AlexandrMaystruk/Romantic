package maystruks08.gmail.com.romantic.core.di.application.root.hike.selectedhike.participant

import dagger.Module
import dagger.Provides

import maystruks08.gmail.com.domain.interactor.hike.selectedhike.participant.ParticipantInteractor
import maystruks08.gmail.com.domain.interactor.hike.selectedhike.participant.ParticipantInteractorImpl
import maystruks08.gmail.com.romantic.ui.hike.selectedhike.participant.ParticipantContract
import maystruks08.gmail.com.romantic.ui.hike.selectedhike.participant.ParticipantPresenter
import maystruks08.gmail.com.romantic.ui.hike.selectedhike.invitetogroup.InviteUserContract
import maystruks08.gmail.com.romantic.ui.hike.selectedhike.invitetogroup.UserPresenter

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

}