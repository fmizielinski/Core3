package example.core3.view.main.list.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import example.core3.domain.dto.PostDto
import kotlinx.android.synthetic.main.item_post.view.*

class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(post: PostDto) {
        itemView.textPostTitle.text = post.title
        itemView.textPostBody.text = post.body
    }
}