fun main() {
    val service = WallService()
    service.add(Post(text = "Текст №1"))
    service.add(Post(text = "Текст №2"))
    service.add(Post(text = "Текст №3"))

    printPosts(service.posts)

    service.update(Post(3, text = "Текст №3 обновлён", ownerId = 34, date = 2011))

    println("Посты после обновления:")
    printPosts(service.posts)
}

fun printPosts(posts: Array<Post>): Boolean {
    for ((index) in posts.withIndex())
        println("id = ${posts[index].id} text = ${posts[index].text} ownerId = ${posts[index].ownerId} date = ${posts[index].date}")
    return true
}

