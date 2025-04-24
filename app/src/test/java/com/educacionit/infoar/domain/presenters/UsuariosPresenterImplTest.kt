package com.educacionit.infoar.domain.presenters

import com.educacionit.infoar.domain.contracts.repository.UsuariosRepository
import com.educacionit.infoar.domain.contracts.vistas.UsuariosView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class UsuariosPresenterImplTest {

    private lateinit var presenter: UsuariosPresenterImpl

    @Mock
    lateinit var testView: UsuariosView

    @Mock
    lateinit var testRepository: UsuariosRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this) // VERSION 5.11
        //MockitoAnnotations.initMocks(this) // VERSION 2.19
        presenter = UsuariosPresenterImpl(
            uiContext = Dispatchers.Unconfined,
            ioContext = Dispatchers.Unconfined,
            view = testView,
            repository = testRepository
        )
    }

    @Test
    fun `init should get user list`() {
        runBlocking {
            // Given

            // When
            presenter.init()

            // Assert
            verify(testRepository).getUsuarios()
        }
    }

    @Test
    fun `init should update loading`() {
        // Given


        // When
        presenter.init()


        // Assert
        verify(testView).showLoading()
        verify(testView).hideLoading()
    }

    @Test
    fun clearShouldReleaseReferences() {
        // Given
        presenter.init()

        // When
        presenter.clear()


        // Assert
        assertNull(presenter.getView())
        assertTrue(presenter.getJob().isCancelled)
    }
}