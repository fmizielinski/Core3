package example.core3.view.main.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import example.core3.R
import example.core3.domain.dto.CommentDto

class CommentAdapter : RecyclerView.Adapter<CommentViewHolder>() {

    var list: List<CommentDto> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val item = list[position]

        holder.bind(item)
    }
}