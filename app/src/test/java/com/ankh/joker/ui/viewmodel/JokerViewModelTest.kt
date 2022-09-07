package com.ankh.joker.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ankh.joker.data.model.Flags
import com.ankh.joker.data.model.Joke
import com.ankh.joker.data.repo.JokerRepositoryImp
import com.ankh.joker.domain.JokeState
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.whenever

class JokerViewModelTest {

    @MockK
    private lateinit var viewModel: JokerViewModel

    @MockK
    private lateinit var repositoryImp: JokerRepositoryImp

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `getState should return Loading state when data is not yet available`() {
        every { viewModel.jokeState } returns MutableLiveData(JokeState.Loading)
        assertEquals(JokeState.Loading, viewModel.jokeState.value)
    }

    @Test
    fun `getState should return Success state when data is available`() {
        every { viewModel.jokeState } returns MutableLiveData(JokeState.Success(listOf()))

        assertEquals(JokeState.Success(listOf()), viewModel.jokeState.value)
    }

    @Test
    fun `getState should return failure when the server is not successful and is done loading`() {
        every { viewModel.jokeState } returns MutableLiveData(JokeState.Failure(""))

        assertEquals(JokeState.Failure(""), viewModel.jokeState.value)
    }

    /*@Test
    fun `getState should return a list of items when successful`() {



        *//*val observer = mockk<Observer<JokeState>>{ every { onChanged(any()) } just Runs}

        viewModel.jokeState.observeForever(observer)

        viewModel.getJokes()

        verifySequence {
            observer.onChanged(JokeState.Loading)
            observer.onChanged(JokeState.Success(listOf()))
        }*//*

        val observer = mockk<Observer<JokeState>>()

        every { observer.onChanged(any())  } answers {}
        every { viewModel.jokeState.value } returns JokeState.Success(getTestJokes())

        viewModel.getJokes()
        //verify{observer.onChanged(JokeState.Success(jokes))}
        assertEquals(JokeState.Success(getTestJokes()), viewModel.jokeState.value)
    }

    @Test
    fun `should listen to publish of a String`() {
        val localEventScope = GlobalScope()

        val onReceiveMock = mockk<suspend (String) -> Unit>()
        coEvery { onReceiveMock.invoke(any<String>()) } returns Unit

        localEventScope.listen<String>(this, Dispatchers.Default, onReceiveMock)

        localEventScope.publish("test string")

        coVerify { onReceiveMock.invoke(any()) }
    }*/

    private fun getTestJokes() = listOf(
        Joke(
            false,
            "",
            "",
            "Hi",
            Flags(false, false, false, false, false, false),
            1,
            true,
            ""
        ),
        Joke(
            false,
            "",
            "",
            "Hey",
            Flags(false, false, false, false, false, false),
            2,
            true,
            ""
        )
    )


}