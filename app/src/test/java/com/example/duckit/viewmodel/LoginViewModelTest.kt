package com.example.duckit.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import com.example.duckit.repository.DuckItRepository
import com.example.duckit.repository.LoginError
import com.example.duckit.repository.SignUpError
import com.example.duckit.store.LoginState
import com.example.duckit.ui.component.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: LoginViewModel
    private lateinit var mockDuckItRepository: DuckItRepository
    private lateinit var mockLoginState: LoginState

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        mockDuckItRepository = mock()
        mockLoginState = mock()
        viewModel = LoginViewModel(mockLoginState, mockDuckItRepository)
    }

    @Test
    fun `verify that login calls repository and updates state`() = runTest {
        val username = "testUser"
        val password = "testPassword"
        val mockNavController = mock(NavController::class.java)
        whenever(mockDuckItRepository.login(username, password)).thenReturn(Result.success("authToken"))
        viewModel.onLoginAction(mockNavController, username, password)
        testDispatcher.scheduler.advanceUntilIdle()

        verify(mockLoginState).setAuthToken("authToken")
        verify(mockNavController).navigateUp()
    }

    @Test
    fun `verify that when login fails, navigate to login error dialog`() = runTest {
        val username = "testUser"
        val password = "testPassword"
        val mockNavController = mock(NavController::class.java)
        whenever(mockDuckItRepository.login(username, password)).thenReturn(Result.failure(
            LoginError(403)
        ))
        viewModel.onLoginAction(mockNavController, username, password)
        testDispatcher.scheduler.advanceUntilIdle()

        verify(mockNavController).navigate(Screen.LoginErrorDialog.route)
    }

    @Test
    fun `verify that when no account exists, navigate to signup flow`() = runTest {
        val username = "testUser"
        val password = "testPassword"
        val mockNavController = mock(NavController::class.java)
        whenever(mockDuckItRepository.login(username, password)).thenReturn(Result.failure(
            LoginError(404)
        ))
        viewModel.onLoginAction(mockNavController, username, password)
        testDispatcher.scheduler.advanceUntilIdle()

        verify(mockNavController).navigate(Screen.SignUp.route)
    }

    @Test
    fun `verify that register calls repository and updates state`() = runTest {
        val username = "testUser"
        val password = "testPassword"
        val mockNavController = mock(NavController::class.java)

        whenever(mockDuckItRepository.register(username, password)).thenReturn(Result.success("authToken"))
        viewModel.onRegisterAction(mockNavController, username, password)
        testDispatcher.scheduler.advanceUntilIdle()

        verify(mockLoginState).setAuthToken("authToken")
        verify(mockNavController).navigate(Screen.SignUpSuccessDialog.route)
    }

    @Test
    fun `verify that when register fails, navigate to signup error dialog`() = runTest {
        val username = "testUser"
        val password = "testPassword"
        val mockNavController = mock(NavController::class.java)
        whenever(mockDuckItRepository.register(username, password)).thenReturn(Result.failure(SignUpError(403)))
        viewModel.onRegisterAction(mockNavController, username, password)
        testDispatcher.scheduler.advanceUntilIdle()
        verify(mockNavController).navigate(Screen.SignUpErrorDialog.route)
    }
}