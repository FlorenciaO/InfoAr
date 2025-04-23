package com.educacionit.infoar.presentation.fragments

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.educacionit.infoar.InfoArApp.Companion.CHANNEL_ID
import com.educacionit.infoar.R
import com.educacionit.infoar.presentation.adapters.NoticiasListAdapter
import com.educacionit.infoar.presentation.adapters.NoticiasListAdapter.NoticiasListAdapterListener
import com.educacionit.infoar.databinding.FragmentNoticiasBinding
import com.educacionit.infoar.domain.models.Noticia
import com.educacionit.infoar.presentation.HomeActivity
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoticiasFragment : Fragment(), NoticiasListAdapterListener {

    private var _binding: FragmentNoticiasBinding? = null
    private val binding get() = _binding!!
    private val newsAdapter = NoticiasListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNoticiasBinding.inflate(inflater, container, false)

        binding.newsList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = newsAdapter
        }

        binding.fab.setOnClickListener {
            createNotification()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) { // Inicio de corrutina
            showLoading()
            val listaDeNoticias: List<Noticia> = fakeData()

            Log.d("NoticiasFragment", "Lista de noticias: $listaDeNoticias")

            finishLoading()
            newsAdapter.setNoticiasList(listaDeNoticias)
        }

        // Lanzamos una corutina en el dispatcher IO (hilo secundario)
        /* CoroutineScope(Dispatchers.Main).launch {
            // Operacion
            delay(2000) // simulamos una operación que tarda

            // ERROR: Intentamos modificar la UI desde un hilo que no es el principal
            // Exception lanzada: android.view.ViewRootImpl$CalledFromWrongThreadException
            Log.d("NoticiasFragment", "Intentamos modificar la UI desde un hilo que no es el principal")
            binding.title.visibility = View.GONE
            binding.title.text = "Intentamos modificar la UI desde un hilo que no es el principal"

            /**
             * Moraleja:
             * Android no garantiza que siempre se lanzará una excepción cuando tocás la UI desde un hilo secundario.
             * Aunque la documentación es clara en cuanto a que está prohibido, la realidad técnica va a depender de varios factores.
             * No todas las propiedades de una View validan el hilo.
             * textView.text = "..." puede pasar "silenciosamente", dependiendo del Android API level y fabricante.
             *
             * @see https://developer.android.com/topic/performance/threads?hl=es-419
             */
        } */
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onNewsItemClicked(newsId: String) {

    }

    private fun createNotification() {
        val notificationManager: NotificationManager = requireContext().getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(requireContext(), HomeActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(requireContext(), 0, intent,
            PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Nueva notificacion")
            .setContentText("Se creó una nueva publicación")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()
        notificationManager.notify(1, notification)
    }

    private fun showLoading() {
        binding.circularProgressIndicator.visibility = View.VISIBLE
    }

    private fun finishLoading() {
        binding.circularProgressIndicator.visibility = View.INVISIBLE
    }

    private suspend fun fakeData(): List<Noticia> = withContext(Dispatchers.IO) {
        delay(2000)

        listOf(
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