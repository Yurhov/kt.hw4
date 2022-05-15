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
}