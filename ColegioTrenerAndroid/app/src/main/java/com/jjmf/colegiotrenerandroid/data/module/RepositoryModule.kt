package com.jjmf.colegiotrenerandroid.data.module

import com.jjmf.colegiotrenerandroid.data.repository.AsistenciaRepositoryImpl
import com.jjmf.colegiotrenerandroid.domain.repository.AuthRepository
import com.jjmf.colegiotrenerandroid.domain.repository.ComboRepository
import com.jjmf.colegiotrenerandroid.domain.repository.PagosRepository
import com.jjmf.colegiotrenerandroid.domain.repository.PersonaRepository
import com.jjmf.colegiotrenerandroid.data.repository.AuthRepositoryImpl
import com.jjmf.colegiotrenerandroid.data.repository.AutorizacionRepositoryImpl
import com.jjmf.colegiotrenerandroid.data.repository.CitaInformeRepositoryImpl
import com.jjmf.colegiotrenerandroid.data.repository.ComboRepositoryImpl
import com.jjmf.colegiotrenerandroid.data.repository.InscripcionesRepositoryImpl
import com.jjmf.colegiotrenerandroid.data.repository.NotifacionRepositoryImpl
import com.jjmf.colegiotrenerandroid.data.repository.PagosRepositoryImpl
import com.jjmf.colegiotrenerandroid.data.repository.PersonaRepositoryImpl
import com.jjmf.colegiotrenerandroid.data.repository.TareaRepositoryImpl
import com.jjmf.colegiotrenerandroid.domain.repository.AsistenciaRepository
import com.jjmf.colegiotrenerandroid.domain.repository.AutorizacionRepository
import com.jjmf.colegiotrenerandroid.domain.repository.CitaInformeRepository
import com.jjmf.colegiotrenerandroid.domain.repository.InscripcionesRepository
import com.jjmf.colegiotrenerandroid.domain.repository.NotifacionRepository
import com.jjmf.colegiotrenerandroid.domain.repository.TareaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun authRepo(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun personRepo(impl: PersonaRepositoryImpl): PersonaRepository

    @Binds
    abstract fun comboRepo(impl: ComboRepositoryImpl): ComboRepository

    @Binds
    abstract fun pagoRepo(impl: PagosRepositoryImpl): PagosRepository

    @Binds
    abstract fun inscripcionRepo(impl: InscripcionesRepositoryImpl): InscripcionesRepository

    @Binds
    abstract fun asistenciaRepo(impl: AsistenciaRepositoryImpl): AsistenciaRepository

    @Binds
    abstract fun citaInformeRepo(impl: CitaInformeRepositoryImpl): CitaInformeRepository

    @Binds
    abstract fun autorizacionRepo(impl: AutorizacionRepositoryImpl): AutorizacionRepository

    @Binds
    abstract fun tarea(impl: TareaRepositoryImpl): TareaRepository

    @Binds
    abstract fun notifacionRepo(impl: NotifacionRepositoryImpl): NotifacionRepository

}