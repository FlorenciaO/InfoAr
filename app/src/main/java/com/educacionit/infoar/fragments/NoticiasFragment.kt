package com.educacionit.infoar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.educacionit.infoar.R
import com.educacionit.infoar.adapters.NoticiasListAdapter
import com.educacionit.infoar.adapters.NoticiasListAdapter.NoticiasListAdapterListener
import com.educacionit.infoar.databinding.FragmentNoticiasBinding
import com.educacionit.infoar.models.Noticia
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class NoticiasFragment : Fragment(), NoticiasListAdapterListener {

    private var _binding: FragmentNoticiasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoticiasBinding.inflate(inflater, container, false)

        val newsAdapter = NoticiasListAdapter(this)
        binding.newsList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = newsAdapter
        }

        // TODO(Obtener usuarios y setear lista en adapter)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onNewsItemClicked(newsId: String) {

    }

    private fun showLoading() {
        binding.circularProgressIndicator.visibility = View.VISIBLE
    }

    private fun finishLoading() {
        binding.circularProgressIndicator.visibility = View.INVISIBLE
    }

    private fun fakeData(): List<Noticia> {
        return listOf(
            Noticia(
                id = "1",
                title = "Asaltaron un banco de Buenos Aires",
                content = "Asaltaron un banco de Buenos Aires, detalles adicionales",
                image = "https://images.unsplash.com/photo-1501167786227-4cba60f6d58f?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                byUser = "Rolando Graña"
            )
        )
    }
}