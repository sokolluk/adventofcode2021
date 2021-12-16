package cz.sokolluk.adventofcode2021.feature_tasks.di

import android.content.Context
import cz.sokolluk.adventofcode2021.feature_tasks.data.repository.TaskRepositoryImpl
import cz.sokolluk.adventofcode2021.feature_tasks.data.service.TaskService
import cz.sokolluk.adventofcode2021.feature_tasks.domain.repository.TaskRepository
import cz.sokolluk.adventofcode2021.feature_tasks.domain.use_case.GetTaskResult
import cz.sokolluk.adventofcode2021.feature_tasks.domain.use_case.GetTasks
import cz.sokolluk.adventofcode2021.feature_tasks.domain.use_case.TaskUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Provides
    @Singleton
    fun provideTaskService(@ApplicationContext context: Context): TaskService {
        return TaskService.create(context)
    }

    @Provides
    @Singleton
    fun provideTaskRepository(service: TaskService): TaskRepository {
        return TaskRepositoryImpl(service)
    }

    @Provides
    @Singleton
    fun provideTaskUseCases(repository: TaskRepository): TaskUseCases {
        return TaskUseCases(
            getTasks = GetTasks(repository),
            getTaskResult = GetTaskResult(repository),
        )
    }
}