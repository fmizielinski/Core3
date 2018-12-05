package example.core3.view.main.details.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import example.core3.R
import example.core3.domain.dto.CommentDto
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(comment: CommentDto) {
        itemView.textCommentTitle.text = comment.name
        itemView.textCommentBody.text = comment.body
        itemView.textCommentAuthor.text =
                String.format(itemView.resources.getString(R.string.all_author), comment.email)
    }
}