package com.example.rick_morty_compose.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rick_morty_compose.domain.models.CharacterModel
import com.example.rick_morty_compose.domain.repository.ResponseWrapper
import com.example.rick_morty_compose.domain.usecases.GetCharacterByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailVM @Inject constructor(
    private val getCharacterUseCase: GetCharacterByIdUseCase,
//    private val id: Long,
) : ViewModel() {

    private val _character = MutableLiveData<CharacterModel?>()
    val character: LiveData<CharacterModel?> = _character

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getCharacter(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getCharacterUseCase(id)
            _isLoading.postValue(false)

            when (response) {
                is ResponseWrapper.Error -> {
                    _error.postValue("Tenemos problemas para comunicarnos con nuestros servidores (${response.code}")
                }

                ResponseWrapper.NetworkError -> {
                    _error.postValue("Revisa tu conexion a internet")
                }

                is ResponseWrapper.Success -> _character.postValue(response.data)
            }
        }
    }
}