package maystruks08.gmail.com.domain.entity

data class SyncResult(val notUploadHikeCount: Int, val notUploadParticipantCount: Int) {

    companion object {
        fun success() = SyncResult(0, 0)
    }

    fun isSuccess(): Boolean {
        return notUploadHikeCount == 0 && notUploadParticipantCount == 0
    }
}