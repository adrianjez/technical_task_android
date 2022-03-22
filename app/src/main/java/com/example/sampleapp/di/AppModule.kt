package com.example.sampleapp.di

import com.example.sampleapp.api.GorestApi
import com.example.sampleapp.data.repository.IUserRepository
import com.example.sampleapp.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  @Singleton
  fun provideRetrofit(client: OkHttpClient): Retrofit =
    Retrofit.Builder()
      .baseUrl(GorestApi.BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .client(client)
      .build()

  @Provides
  @Singleton
  fun provideApi(retrofit: Retrofit): GorestApi = retrofit.create(GorestApi::class.java)

  @Provides
  @Singleton
  fun provideOkHttpClient(
    @Named("authentication_interceptor") authenticationInterceptor: Interceptor
  ) : OkHttpClient {
    return OkHttpClient.Builder().addInterceptor(authenticationInterceptor).build()
  }

  @Provides
  @Singleton
  @Named("authentication_interceptor")
  fun provideAuthenticationInterceptor() = Interceptor { chain ->
    chain.run {
      proceed(
        request()
          .newBuilder()
          .addHeader("Authorization", "Bearer 2c2a91bd7f1dc1267b73d8b4def9fc3862c0617762a7abc0ad4a6d0bc195479e")
          .build()
      )
    }
  }

  @Provides
  @Singleton
  fun provideUserRepository(userRepository: UserRepository) : IUserRepository = userRepository
}