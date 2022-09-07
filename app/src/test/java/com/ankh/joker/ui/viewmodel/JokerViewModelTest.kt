package com.ankh.joker.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ankh.joker.data.repo.JokerRepositoryImp
import com.ankh.joker.domain.JokeState
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.kotlin.whenever

class JokerViewModelTest{

    private lateinit var viewModel: JokerViewModel

    @Mock
    private lateinit var repositoryImp: JokerRepositoryImp

    @Before
    fun setup(){
        initMocks(this)
        viewModel = JokerViewModel(repositoryImp)
    }

    @Test
    fun `getState should return Loading state when data is not yet available`(){
        whenever(repositoryImp.getSingleJoke()).thenReturn(MutableLiveData(JokeState.Loading))

       // viewModel.jokeState
        assertEquals(JokeState.Loading,viewModel.jokeState)
    }

}