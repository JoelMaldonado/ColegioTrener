package com.jjmf.colegiotrenerandroid.domain.repository

import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.domain.model.Club
import com.jjmf.colegiotrenerandroid.domain.model.Distrito

interface ComboRepository {
    suspend fun getClubes(): Result<List<Club>>
    suspend fun getDistritos(): Result<List<Distrito>>
}