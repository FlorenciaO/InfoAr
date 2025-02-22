package com.educacionit.infoar.presentacion.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.educacionit.infoar.presentacion.adapters.UsuariosListAdapter.UsuarioViewHolder
import com.educacionit.infoar.databinding.ViewUserItemBinding
import com.educacionit.infoar.models.Usuario

class UsuariosListAdapter(
    private val listener: UsuariosListAdapterListener,
    private var list: MutableList<Usuario> = mutableListOf()
) : RecyclerView.Adapter<UsuarioViewHolder>() {

    interface UsuariosListAdapterListener {
        fun onGoToMapClicked(userId: String, userName: String)
    }

    inner class UsuarioViewHolder(private val binding: ViewUserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: Usuario) {
            with(binding) {
                with(user) {
                    usernameTv.text = userName
                    companyTv.text = companyName
                    goToMapTv.setOnClickListener {
                        listener.onGoToMapClicked(id, userName)
                    }
                }
            }
        }
    }

    fun setUsuariosList(newList: List<Usuario>) {
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

    private fun areItemsTheSame(oldUser: Usuario, user: Usuario): Boolean {
        return oldUser.id == user.id
    }

    private fun areContentsTheSame(oldUser: Usuario, user: Usuario): Boolean {
        return oldUser == user
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        return UsuarioViewHolder(
            ViewUserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        holder.bind(list[position])
    }
}
