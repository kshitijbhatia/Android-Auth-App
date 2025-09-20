package com.example.authapp.di

import com.example.authapp.dataSource.AuthenticationDataSource
import com.example.authapp.dataSource.AuthenticationDataSourceImpl
import com.example.authapp.dataSource.AuthenticationRepo
import com.example.authapp.usecase.RegisterUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesAuthenticationDataSource(firebaseAuth: FirebaseAuth): AuthenticationDataSource {
        return AuthenticationDataSourceImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun providesAuthenticationRepo(authenticationDataSource: AuthenticationDataSource): AuthenticationRepo {
        return AuthenticationRepo(authenticationDataSource)
    }

    @Provides
    @Singleton
    fun providesRegisterUseCase(authenticationRepo: AuthenticationRepo): RegisterUseCase {
        return RegisterUseCase(authenticationRepo)
    }
}