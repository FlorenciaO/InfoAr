package com.educacionit.infoar.adapters

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.educacionit.infoar.R
import com.educacionit.infoar.models.Noticia
import com.educacionit.infoar.adapters.NoticiasListAdapter.NoticiaViewHolder
import com.educacionit.infoar.databinding.ViewNewsItemBinding
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

class NoticiasListAdapter(
    private val listener: NoticiasListAdapterListener,
    private var list: MutableList<Noticia> = mutableListOf()
) : RecyclerView.Adapter<NoticiaViewHolder>() {

    private val picasso = Picasso.get()

    interface NoticiasListAdapterListener {
        fun onNewsItemClicked(newsId: String)
    }

    inner class NoticiaViewHolder(private val binding: ViewNewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: Noticia) {
            val resources: Resources = binding.root.context.resources
            with(binding) {
                with(news) {
                    if (image.isNotBlank() && image.isNotEmpty()) {
                        picasso.load(image)
                            .error(R.drawable.no_image_placeholder)
                            .transform(RoundedCornersTransformation(20, 0))
                            .fit()
                            .into(imageView)
                    }
                    titleTv.text = title
                    usernameTimeAgoTv.text = resources.getString(
                        R.string.noticia_usuario_text,
                        byUser,
                    )
                    root.setOnClickListener {
                        listener.onNewsItemClicked(id)
                    }
                }
            }
        }
    }

    fun setNoticiasList(newList: List<Noticia>) {
        val result: DiffUtil.DiffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                areItemsTheSame(list[oldItemPosition], newList[newItemPosition])

            override fun getOldListSize() = list.size

            override fun getNewListSize() = newList.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                areContentsTheSame(list[oldItemPosition], newList[newItemPosition])
        })

        list = newList.toMutableList()
        result.dispatchUpdatesTo(this)
    }

    private fun areItemsTheSame(oldNews: Noticia, news: Noticia): Boolean {
        return oldNews.id == news.id
    }

    private fun areContentsTheSame(oldNews: Noticia, news: Noticia): Boolean {
        return oldNews == news
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiaViewHolder {
        return NoticiaViewHolder(
            ViewNewsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NoticiaViewHolder, position: Int) {
        holder.bind(list[position])
    }
}
