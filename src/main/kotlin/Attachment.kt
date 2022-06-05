sealed class Attachment {
    abstract val type: String
}

data class AttachmentPhoto(
    override val type: String = "photo",
    val photo: Photo
) : Attachment()

data class AttachmentVideo(
    override val type: String = "video",
    val video: Video
) : Attachment()

data class AttachmentAudio(
    override val type: String = "audio",
    val audio: Audio
) : Attachment()

data class AttachmentDoc(
    override val type: String = "doc",
    val doc: Doc
) : Attachment()

data class AttachmentLink(
    override val type: String = "link",
    val link: Link
) : Attachment()