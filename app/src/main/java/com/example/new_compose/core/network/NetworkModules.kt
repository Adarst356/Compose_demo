package com.example.new_compose.core.network
import com.example.new_compose.core.utils.Constants.BASE_URL
import com.example.new_compose.core.utils.PrettyPrinterInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providePrettyPrinter(): PrettyPrinterInterceptor {
        return PrettyPrinterInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        prettyPrinterInterceptor: PrettyPrinterInterceptor
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(prettyPrinterInterceptor)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(5, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiClient(retrofit: Retrofit): ApiClient {
        return retrofit.create(ApiClient::class.java)
    }
}
