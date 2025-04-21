package com.educacionit.infoar.presentation.fragments

import android.graphics.drawable.ShapeDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.educacionit.infoar.R
import com.educacionit.infoar.data.UsuariosRepositoryImpl
import com.educacionit.infoar.databinding.FragmentUsuariosBinding
import com.educacionit.infoar.domain.contracts.presenters.UsuariosPresenter
import com.educacionit.infoar.domain.contracts.vistas.UsuariosView
import com.educacionit.infoar.domain.models.Usuario
import com.educacionit.infoar.domain.presenters.UsuariosPresenterImpl
import com.educacionit.infoar.presentation.adapters.UsuariosListAdapter

class UsuariosFragment : Fragment(), UsuariosListAdapter.UsuariosListAdapterListener, UsuariosView {

    private var _binding: FragmentUsuariosBinding? = null
    private val binding get() = _binding!!
    private val usersAdapter = UsuariosListAdapter(this)
    private lateinit var presenter: UsuariosPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentUsuariosBinding.inflate(inflater, container, false)

        presenter = UsuariosPresenterImpl(
            repository = UsuariosRepositoryImpl(requireContext()),
            view = this
        )

        binding.usersList.apply {
            val orientation = RecyclerView.VERTICAL
            val itemDecoration = DividerItemDecoration(context, orientation)
            val drawable = ShapeDrawable().apply {
                paint.color = ContextCompat.getColor(context, R.color.teal_200);
            }
            itemDecoration.setDrawable(drawable)
            addItemDecoration(itemDecoration)
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, orientation, false)
            adapter = usersAdapter
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        presenter.clear()
    }

    override fun showList(usuarios: List<Usuario>) {
        usersAdapter.setUsuariosList(usuarios)
    }

    override fun showLoading() {
        binding.circularProgressIndicator.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.circularProgressIndicator.visibility = View.INVISIBLE
    }

    override fun onGoToMapClicked(userId: String, userName: String) {

    }
}
