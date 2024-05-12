package com.example.rick_morty_compose.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rick_morty_compose.domain.models.CharacterModel
import com.example.rick_morty_compose.domain.repository.ResponseWrapper
import com.example.rick_morty_compose.domain.usecases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
) : ViewModel() {

    private val _characters = MutableLiveData(listOf<CharacterModel>())
    val characters: LiveData<List<CharacterModel>> = _characters

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        getAllCharacters()
    }

    private fun getAllCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val response = getCharactersUseCase()
            _isLoading.postValue(false)

            when (response) {
                is ResponseWrapper.Error -> {
                    _error.postValue("Tenemos problemas para comunicarnos con nuestros servidores (${response.code}")
                }

                ResponseWrapper.NetworkError -> {
                    _error.postValue("Revisa tu conexion a internet")
                }

                is ResponseWrapper.Success -> _characters.postValue(response.data)
            }
        }
    }

    companion object {
        private val TAG = HomeVM::class.simpleName
    }
}