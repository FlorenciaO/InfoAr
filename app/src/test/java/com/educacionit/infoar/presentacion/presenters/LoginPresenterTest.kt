package com.educacionit.infoar.presentacion.presenters

import com.educacionit.infoar.TestCoroutineRule
import com.educacionit.infoar.dominio.contratos.presenters.LoginPresenter
import com.educacionit.infoar.dominio.contratos.vistas.LoginView
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class LoginPresenterTest {

    @get:Rule
    val coroutineTestRule = TestCoroutineRule()

    @Mock
    lateinit var loginView: LoginView

    lateinit var presenter: LoginPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        // Init presenter
    }

    @Test
    fun `test_method`() {

    }
}