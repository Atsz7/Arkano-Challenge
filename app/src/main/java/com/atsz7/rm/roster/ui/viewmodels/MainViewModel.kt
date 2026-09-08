package com.atsz7.rm.roster.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atsz7.rm.roster.domain.usecases.DownloadCharactersUseCase
import com.atsz7.rm.roster.domain.usecases.GetCharactersUseCase
import com.atsz7.rm.roster.ui.screens.state.MainScreenState
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

    private val _isRefreshing = MutableStateFlow(false)
    private val _isError = MutableStateFlow(false)

    val mainState: StateFlow<MainScreenState> = combine(
        getCharactersUseCase(),
        _isRefreshing,
        _isError
    ) { characters, isRefreshing, isError ->
        when {
            isError -> MainScreenState.Error
            else -> MainScreenState.Success(characters, isRefreshing)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(
            stopTimeoutMillis = STOP_TIMEOUT_IN_MILLIS
        ),
        initialValue = MainScreenState.Success(emptyList(), isRefreshing = true)
    )

    init {
        download(forceRefresh = false)
    }

    fun onRefresh() {
        download(forceRefresh = true)
    }

    private fun download(forceRefresh: Boolean) {
        viewModelScope.launch {
            _isRefreshing.value = true
            _isError.value = false
            try {
                downloadCharactersUseCase(forceRefresh)
            } catch (ex: Exception) {
                Log.e(javaClass.name, ex.message, ex)
                _isError.value = true
            } finally {
                _isRefreshing.value = false
            }
        }
    }

    companion object {
        internal const val STOP_TIMEOUT_IN_MILLIS = 5_000L
    }
}
