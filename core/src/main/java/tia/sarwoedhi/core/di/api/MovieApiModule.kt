package tia.sarwoedhi.core.di.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import tia.sarwoedhi.core.data.remote.api.MovieApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieApiModule {

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

}