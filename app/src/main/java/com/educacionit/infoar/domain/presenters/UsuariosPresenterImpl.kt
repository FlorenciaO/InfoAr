package com.educacionit.infoar.domain.presenters

import com.educacionit.infoar.domain.contracts.presenters.UsuariosPresenter
import com.educacionit.infoar.domain.contracts.repository.UsuariosRepository
import com.educacionit.infoar.domain.contracts.vistas.UsuariosView
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.annotations.TestOnly
import kotlin.coroutines.CoroutineContext

class UsuariosPresenterImpl(
    private val repository: UsuariosRepository,
    private val uiContext: CoroutineContext = Dispatchers.Main,
    private val ioContext: CoroutineContext = Dispatchers.IO,
    private var view: UsuariosView? = null
) : UsuariosPresenter, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = uiContext + job

    private var job: Job = Job()

    override fun init() {
        launch(ioContext) {
            withContext(uiContext) {
                view?.showLoading()
            }

            val lista = repository.getUsuarios()

            withContext(uiContext) {
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

    @TestOnly
    fun getView() = view

    @TestOnly
    fun getJob() = job
}