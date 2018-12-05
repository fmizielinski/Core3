package example.core3.view.main.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import example.core3.R
import example.core3.domain.dto.PostDto
import io.reactivex.subjects.PublishSubject

class PostAdapter : RecyclerView.Adapter<PostViewHolder>() {

    val onItemSelectedListener: PublishSubject<PostDto> = PublishSubject.create()

    var list: List<PostDto> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = list[position]

        holder.bind(item)
        holder.itemView.setOnClickListener { onItemSelectedListener.onNext(item) }
    }
}