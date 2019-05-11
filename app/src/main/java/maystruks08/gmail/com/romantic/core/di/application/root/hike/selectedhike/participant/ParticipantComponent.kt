package maystruks08.gmail.com.romantic.core.di.application.root.hike.selectedhike.participant

import dagger.Subcomponent
import maystruks08.gmail.com.romantic.ui.hike.selectedhike.participant.ParticipantFragment
import maystruks08.gmail.com.romantic.ui.hike.selectedhike.invitetogroup.InviteUserFragment

@Subcomponent(modules = [ParticipantModule::class])
@ParticipantScope
interface ParticipantComponent {

    fun inject(participantFragment: ParticipantFragment)

    fun inject(inviteFragment: InviteUserFragment)


}