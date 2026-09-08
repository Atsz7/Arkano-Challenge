package com.atsz7.rm.roster.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atsz7.rm.roster.domain.usecases.DownloadCharactersUseCase
import com.atsz7.rm.roster.domain.usecases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getCharactersUseCase: GetCharactersUseCase,
    private val downloadCharactersUseCase: DownloadCharactersUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    private val _isError = MutableStateFlow(false)

    val mainState: StateFlow<MainScreenState> = combine(
        getCharactersUseCase(),
        _isLoading,
        _isError
    ) { characters, isLoading, isError ->
        when {
            isError -> MainScreenState.Error
            isLoading -> MainScreenState.Loading
            else -> MainScreenState.Success(characters)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(
            stopTimeoutMillis = STOP_TIMEOUT_IN_MILLIS
        ),
        initialValue = MainScreenState.Idle
    )

    init {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                downloadCharactersUseCase()
            } catch (ex: Exception) {
                Log.e(javaClass.name, ex.message, ex)
                _isError.value = true
            } finally {
                _isLoading.value = false
            }
        }
    }

    companion object {
        internal const val STOP_TIMEOUT_IN_MILLIS = 5_000L
    }
}
