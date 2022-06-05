class PostNotFoundException(message: String) : RuntimeException(message)

class WallService {
    var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()

    fun createComment(comment: Comment): Comment {
        for (anyPost in posts) {
            if (anyPost.id == comment.postId) {
                comments += comment
                return comments.last()
            }
        }
        throw PostNotFoundException("Пост с таким id не существует")
    }

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

    fun findPostById(id: Int): Post? {
        for ((index, currentPost) in posts.withIndex()) {
            if (currentPost.id == id) {
                return posts[index]
            }
        }
        return null
    }

    fun getPostComments(post: Post): String {
        var commentsString = ""
        for (comment in comments) {
            if (comment.replyToComment == post.id) {
                commentsString += comment.message
            }
        }
        return commentsString
    }
}

