package com.educacionit.infoar.fragments

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
import com.educacionit.infoar.adapters.UsuariosListAdapter
import com.educacionit.infoar.databinding.FragmentUsuariosBinding
import com.educacionit.infoar.models.Usuario
import kotlinx.coroutines.launch

class UsuariosFragment : Fragment(), UsuariosListAdapter.UsuariosListAdapterListener {

    private var _binding: FragmentUsuariosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsuariosBinding.inflate(inflater, container, false)

        val usersAdapter = UsuariosListAdapter(this)
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

        // TODO(Obtener usuarios y setear lista en adapter)

        return binding.root
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

    private fun fakeData(): List<Usuario> {
        return listOf(
            Usuario(
                id = "1",
                userName = "Florencia Olivera",
                companyName = "MODO",
                address = Usuario.Address(
                    "",
                    "",
                    0.0,
                    0.0
                )
            )
        )
    }
}
