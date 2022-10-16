package com.anonymous.weatherapp.presentation.di

import com.anonymous.weatherapp.BuildConfig
import com.anonymous.weatherapp.data.api.WeatherService
import com.anonymous.weatherapp.data.mapper.ForeCastNetworkToDomainMapper
import com.anonymous.weatherapp.data.repository.ForeCastRepositoryImpl
import com.anonymous.weatherapp.domain.useCase.GetForeCastUseCaseImpl
import com.anonymous.weatherapp.domain.useCase.GetForecastUseCase
import com.anonymous.weatherapp.domain.repository.ForeCaseRepository
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().apply {
            this.baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
            this.client(httpClient)
        }.build()

    @Singleton
    @Provides
    fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            this.setLevel(HttpLoggingInterceptor.Level.BODY)
        }

    @Singleton
    @Provides
    fun provideOKHTTPClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()

    @Singleton
    @Provides
    fun provideForeCastService(retrofit: Retrofit): WeatherService =
        retrofit.create(WeatherService::class.java)

    @Singleton
    @Provides
    fun provideUseCase(repository: ForeCaseRepository): GetForecastUseCase =
        GetForeCastUseCaseImpl(repository)

    @Singleton
    @Provides
    fun provideRepository(
        weatherService: WeatherService,
        mapper: ForeCastNetworkToDomainMapper
    ): ForeCaseRepository =
        ForeCastRepositoryImpl(weatherService, mapper)

    @Singleton
    @Provides
    fun provideMapper(): ForeCastNetworkToDomainMapper =
        ForeCastNetworkToDomainMapper()
}
