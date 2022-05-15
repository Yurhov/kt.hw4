class WallService {
    var posts = emptyArray<Post>()

    fun add(post: Post): Post {
        posts += if (posts.isEmpty()) {
            post.copy(id = 1)
        } else post.copy(id = posts.last().id + 1)
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, updatePost) in posts.withIndex()) {
            if (updatePost.id == post.id) {
                posts[index] = post.copy(
                    ownerId = updatePost.ownerId,
                    date = updatePost.date
                )
                return true
            }
        }
        return false
    }

    fun LastPostId(): Int = if (posts.isEmpty()) 0 else posts.last().id
}