package com.example.duckit.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.foundation.layout.size
import com.example.duckit.datamodel.DuckItInfo
import com.example.duckit.repository.DuckItRepository
import com.example.duckit.store.LoginState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class DuckItViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: DuckItViewModel
    private lateinit var mockDuckItRepository: DuckItRepository
    private lateinit var mockLoginState: LoginState

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        mockDuckItRepository = mock()
        mockLoginState = mock()
        viewModel = DuckItViewModel(mockLoginState, mockDuckItRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initDuckItInfoList updates duckItViewDataList`() {
        runTest {
            `when`(mockDuckItRepository.getDuckItInfo()).thenReturn(
                Result.success(
                    testDuckItInfoList()
                )
            )
            viewModel.initDuckItInfoList()
            testDispatcher.scheduler.advanceUntilIdle()

            Assert.assertEquals(30, viewModel.duckItViewDataList.size)
        }
    }

    @Test
    fun `initDuckItInfoList failure`() = runTest {
        // Given
        `when`(mockDuckItRepository.getDuckItInfo()).thenReturn(Result.failure(Exception("Error")))

        // When
        viewModel.initDuckItInfoList()
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        Assert.assertEquals(0, viewModel.duckItViewDataList.size)
    }

    @Test
    fun onUpvoteClick() = runTest {
        // Given
        val duckItInfoList = listOf(
            DuckItInfo(id = "1", headline = "Headline 1", image = "image1", upvotes = 5),
        )
        `when`(mockDuckItRepository.getDuckItInfo()).thenReturn(Result.success(duckItInfoList))
        viewModel.initDuckItInfoList()
        testDispatcher.scheduler.advanceUntilIdle()

        // When
        viewModel.onUpvoteClick("1")
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        Assert.assertEquals(6, viewModel.duckItViewDataList[0].upvotes.value)
        verify(mockDuckItRepository).upVote("1")
    }

    @Test
    fun onDownvoteClick() = runTest {
        // Given
        val duckItInfoList = listOf(
            DuckItInfo(id = "1", headline = "Headline 1", image = "image1", upvotes = 5),
        )
        `when`(mockDuckItRepository.getDuckItInfo()).thenReturn(Result.success(duckItInfoList))
        viewModel.initDuckItInfoList()
        testDispatcher.scheduler.advanceUntilIdle()

        // When
        viewModel.onDownvoteClick("1")
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        Assert.assertEquals(4, viewModel.duckItViewDataList[0].upvotes.value)
        verify(mockDuckItRepository).downVote("1")
    }

    private fun testDuckItInfoList(): List<DuckItInfo> {
        return List(30) {
            DuckItInfo(
                id = it.toString(),
                headline = "Headline $it",
                image = "Image $it",
                upvotes = it
            )
        }
    }

}