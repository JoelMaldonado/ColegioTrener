package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.DatosFamiliares.screens.ClubesDatosAdmin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.domain.repository.AuthRepository
import com.jjmf.colegiotrenerandroid.domain.repository.ClubRepository
import com.jjmf.colegiotrenerandroid.domain.repository.PersonaRepository
import com.jjmf.colegiotrenerandroid.data.services.request.AddClubRequest
import com.jjmf.colegiotrenerandroid.domain.model.Club
import com.jjmf.colegiotrenerandroid.domain.model.DataClub
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClubesDatosAdminViewModel @Inject constructor(
    private val repository: PersonaRepository,
    private val repoClub: ClubRepository,
    private val auth: AuthRepository
) : ViewModel() {

    var alertAddClub by mutableStateOf(false)
    var listClubs by mutableStateOf<List<Club>>(emptyList())
    var listDataClubs by mutableStateOf<List<DataClub>>(emptyList())
    var isLoading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    fun getClubs(){
        viewModelScope.launch(Dispatchers.IO){
            try {
                when(val res = repoClub.getClubes()){
                    is Result.Correcto -> listClubs = res.datos ?: emptyList()
                    is Result.Error -> error = res.mensaje
                }
            }catch (e:Exception){
                error = e.message
            }
        }
    }

    fun getDataClubs() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading = true
                when (val res = repository.getClubes()) {
                    is Result.Correcto -> listDataClubs = res.datos ?: emptyList()
                    is Result.Error -> error = res.mensaje
                }
            } catch (e: Exception) {
                error = e.message
            } finally {
                isLoading = false
            }
        }
    }

    fun addClub(data: AddClubRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading = true
                when (val res = repository.addClub(data)) {
                    is Result.Correcto -> {}
                    is Result.Error -> {
                        error = res.mensaje
                    }
                }
            } catch (e: Exception) {
                error = e.message
            } finally {
                getDataClubs()
            }
        }
    }
}