package com.educacionit.infoar.domain.contracts.presenters

// Opcion 1: Utilizar el scope asociado al lifecicle de la vista (Fragment o Activity)
// Opcion 2: Implementar CoroutineScope y manualmente cancelar corrutinas cuando la vista se destruya
interface UsuariosPresenter {
    fun init()
    fun clear()
}