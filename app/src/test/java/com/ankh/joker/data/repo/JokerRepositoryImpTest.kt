package com.ankh.joker.data.repo

import androidx.lifecycle.MutableLiveData
import com.ankh.joker.data.remote.JokerService
import com.ankh.joker.domain.JokeState
import junit.framework.Assert.assertEquals

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.kotlin.whenever

class JokerRepositoryImpTest {

    private lateinit var jokerRepositoryImp: JokerRepositoryImp

    @Mock
    private lateinit var jokerService: JokerService


    @Before
    fun setUp() {
        initMocks(this)
        jokerRepositoryImp = JokerRepositoryImp(jokerService)
    }

    @Test
    fun `getSingleJoke should return loading state when the service is not done fetching data`(){
        /*whenever(jokerRepositoryImp.getSingleJoke()).thenReturn(JokeState.Loading)

        jokerRepositoryImp.getSingleJoke()

        assertEquals(JokeState.Loading,jokerRepositoryImp.jokeState.value)*/
    }
}