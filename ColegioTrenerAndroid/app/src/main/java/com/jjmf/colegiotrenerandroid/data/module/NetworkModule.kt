package com.jjmf.colegiotrenerandroid.data.module

import com.jjmf.colegiotrenerandroid.data.services.ApiService
import com.jjmf.colegiotrenerandroid.data.services.AsistenciaService
import com.jjmf.colegiotrenerandroid.data.services.AutorizacionService
import com.jjmf.colegiotrenerandroid.data.services.CitaInformeService
import com.jjmf.colegiotrenerandroid.data.services.InscripcionesService
import com.jjmf.colegiotrenerandroid.data.services.PagoService
import com.jjmf.colegiotrenerandroid.data.services.TareaService
import com.jjmf.colegiotrenerandroid.util.Constantes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttp(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient.addInterceptor(interceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constantes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providePagoService(retrofit: Retrofit): PagoService {
        return retrofit.create(PagoService::class.java)
    }

    @Singleton
    @Provides
    fun provideInscripcionService(retrofit: Retrofit): InscripcionesService {
        return retrofit.create(InscripcionesService::class.java)
    }

    @Singleton
    @Provides
    fun provideAsistenciaService(retrofit: Retrofit): AsistenciaService {
        return retrofit.create(AsistenciaService::class.java)
    }

    @Singleton
    @Provides
    fun provideCitaInformeService(retrofit: Retrofit): CitaInformeService {
        return retrofit.create(CitaInformeService::class.java)
    }
    @Singleton
    @Provides
    fun provideAutorizacionService(retrofit: Retrofit): AutorizacionService {
        return retrofit.create(AutorizacionService::class.java)
    }
    @Singleton
    @Provides
    fun provideTareaService(retrofit: Retrofit): TareaService {
        return retrofit.create(TareaService::class.java)
    }
}