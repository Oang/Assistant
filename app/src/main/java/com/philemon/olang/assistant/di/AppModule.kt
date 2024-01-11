package com.philemon.olang.assistant.di

import android.content.Context
import androidx.room.Room
import com.philemon.olang.assistant.data.local.DB_NAME
import com.philemon.olang.assistant.data.local.MessagesDao
import com.philemon.olang.assistant.data.local.MessagesDatabase
import com.philemon.olang.assistant.data.network.GeminiApi
import com.philemon.olang.assistant.util.MLManager
import com.philemon.olang.assistant.util.PDFManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton
import com.philemon.olang.assistant.domain.repository.ChatRepository
import com.philemon.olang.assistant.domain.repository.PreferencesRepository
import com.philemon.olang.assistant.data.repository.ChatRepositoryImpl
import com.philemon.olang.assistant.data.repository.DataStoreRepository

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRoomDB(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            MessagesDatabase::class.java,
            DB_NAME
        ).build()

    @Provides
    @Singleton
    fun provideMessagesDao(db: MessagesDatabase) = db.messagesDao()

    @Provides
    @Singleton
    fun provideMLManager(
        @ApplicationContext context: Context
    ) = MLManager(context)

    @Provides
    @Singleton
    fun providePDFManager(
        @ApplicationContext context: Context
    ) = PDFManager(context)

    @Provides
    @Singleton
    fun provideChatRepository(
        dao: MessagesDao,
        geminiApi: GeminiApi,
        ml: MLManager,
        pdf: PDFManager
    ): ChatRepository = ChatRepositoryImpl(
        dao,
        geminiApi,
        ml,
        pdf
    )

    @Provides
    @Singleton
    fun provideKtorClient() = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    @Provides
    @Singleton
    fun providePalmApi(
        client: HttpClient
    ): GeminiApi = GeminiApi(client)

    @Provides
    @Singleton
    fun providePreferencesRepository(
        @ApplicationContext context: Context
    ): PreferencesRepository = DataStoreRepository(
        context
    )

}