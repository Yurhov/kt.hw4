import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class WallServiceTest {

    @Test
    fun add() {
        val service = WallService()
        val expected = service.LastPostId() + 1

        service.add(post = Post())

        assertEquals(expected, service.LastPostId())
    }

    @Test
    fun updateExisting() {
        val service = WallService()

        service.add(Post(text = "Текст №1"))
        service.add(Post(text = "Текст №2"))
        service.add(Post(text = "Текст №3"))

        val update = Post(3, text = "Текст №3 обновлён")
        val result = service.update(update)

        Assert.assertTrue(result)

    }

    @Test
    fun updateNotExisting() {
        val service = WallService()
        service.add(Post(text = "Текст 1"))
        service.add(Post(text = "Текст 2"))
        service.add(Post(text = "Текст 3"))

        val updatePost = Post(5, text = "Текст 3 обновлен")
        val result = service.update(updatePost)
        Assert.assertTrue(!result)
    }

    @Test
    fun createComment() {
        val service = WallService()
        val post = Post(text = "Comment")
        service.add(post)

        val comment = Comment(
            replyToComment = service.LastPostId(),
            message = "comment",
            ownerId = 1,
            postId = 1,
            attachments = arrayOf(CommentAttachment("att", 1, 1))
        )

        service.createComment(comment)
        val result = service.findPostById(service.LastPostId())?.let { service.getPostComments(it) }


        assertEquals("comment", result)
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        val service = WallService()
        val post = Post(text = "Comment")
        service.add(post)

        val comment = Comment(
            replyToComment = service.LastPostId(), message = "comment",
            ownerId = 1,
            postId = 2,
            attachments = arrayOf(CommentAttachment("att", 1, 1))
        )
        service.createComment(comment)
    }
}