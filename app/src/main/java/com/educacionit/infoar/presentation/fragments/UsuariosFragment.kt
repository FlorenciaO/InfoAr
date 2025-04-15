package com.educacionit.infoar.presentation.fragments

import android.graphics.drawable.ShapeDrawable
import android.os.Bundle
import android.util.Log
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
import com.educacionit.infoar.presentation.adapters.UsuariosListAdapter
import com.educacionit.infoar.data.UsuariosRepositoryImpl
import com.educacionit.infoar.databinding.FragmentUsuariosBinding
import com.educacionit.infoar.domain.models.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsuariosFragment : Fragment(), UsuariosListAdapter.UsuariosListAdapterListener {

    private var _binding: FragmentUsuariosBinding? = null
    private val binding get() = _binding!!
    private val usersAdapter = UsuariosListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentUsuariosBinding.inflate(inflater, container, false)


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
        // TODO(Obtener usuarios y setear lista en adapter)

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) { // Inicio de corrutina
            showLoading()

            val listaDeUsuarios = fakeData()

            Log.d("UsuariosFragment", "Lista de usuarios: $listaDeUsuarios")

            finishLoading()

            usersAdapter.setUsuariosList(listaDeUsuarios)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading() {
        binding.circularProgressIndicator.visibility = View.VISIBLE
    }

    private fun finishLoading() {
        binding.circularProgressIndicator.visibility = View.INVISIBLE
    }

    override fun onGoToMapClicked(userId: String, userName: String) {
    }

    private suspend fun fakeData(): List<Usuario> = withContext(Dispatchers.IO) {
        val repository = UsuariosRepositoryImpl(requireContext())
        repository.getUsuarios()
    }
}
