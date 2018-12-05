package example.core3.data.network

import example.core3.data.entity.Comment
import example.core3.data.entity.Post
import example.core3.data.entity.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/posts")
    @Headers(
        "Accept: application/json"
    )
    fun getPosts(): Single<List<Post>>

    @GET("/comments")
    @Headers(
        "Accept: application/json"
    )
    fun getComments(@Query("postId") postId: Long): Single<List<Comment>>

    @GET("/users/{userId}")
    @Headers(
        "Accept: application/json"
    )
    fun getUser(@Path("userId") userId: Long): Single<User>
}