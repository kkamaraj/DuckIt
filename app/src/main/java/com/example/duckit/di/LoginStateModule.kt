package com.example.duckit.di

import com.example.duckit.store.LoginState
import com.example.duckit.store.LoginStateImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginStateModule {
    @Provides
    @Singleton
    fun provideLoginState(): LoginState {
        return LoginStateImpl()
    }
}