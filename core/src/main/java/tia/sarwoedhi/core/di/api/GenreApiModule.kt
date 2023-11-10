package tia.sarwoedhi.core.di.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import tia.sarwoedhi.core.data.remote.api.GenreApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GenreApiModule {

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): GenreApi {
        return retrofit.create(GenreApi::class.java)
    }

}