package com.educacionit.infoar.domain.presenters

import com.educacionit.infoar.domain.contracts.presenters.UsuariosPresenter
import com.educacionit.infoar.domain.contracts.repository.UsuariosRepository
import com.educacionit.infoar.domain.contracts.vistas.UsuariosView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class UsuariosPresenterImpl(
    private val repository: UsuariosRepository,
    private val uiContext: CoroutineContext = Dispatchers.Main,
    private var view: UsuariosView? = null
) : UsuariosPresenter, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = uiContext + job

    private var job: Job = Job()

    override fun init() {
        launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                view?.showLoading()
            }

            val lista = repository.getUsuarios()

            withContext(Dispatchers.Main) {
                view?.showList(usuarios = lista)
                view?.hideLoading()
            }
        }
    }

    override fun clear() {
        view = null
        // Cancelar las corrutinas
        job.cancel()
    }
}