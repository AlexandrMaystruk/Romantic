package maystruks08.gmail.com.romantic.core.di.selectedhike.participant

import dagger.Subcomponent
import maystruks08.gmail.com.romantic.ui.selectedhike.participant.ParticipantFragment

@Subcomponent(modules = [ParticipantModule::class])
@ParticipantScope
interface ParticipantComponent {

    fun inject(participantFragment: ParticipantFragment)

}