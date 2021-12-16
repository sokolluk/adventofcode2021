package cz.sokolluk.adventofcode2021.feature_tasks.presentation.task_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

import androidx.lifecycle.viewModelScope
import cz.sokolluk.adventofcode2021.core.util.Resource
import cz.sokolluk.adventofcode2021.feature_tasks.data.service.Task
import cz.sokolluk.adventofcode2021.feature_tasks.domain.use_case.TaskUseCases
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val useCases: TaskUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(TaskDetailState())
    val state: State<TaskDetailState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<Int>("taskId")?.let { taskId ->
            if(taskId != -1) {
                launchGetTaskInput(taskId)
            }
        }
    }

    private fun launchGetTaskInput(taskId: Int) {
        viewModelScope.launch {
            useCases.getTaskResult(taskId)
                .onEach { result ->
                    when(result) {
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                taskNumber = taskId,
                                taskResult = result.data ?: Task.Companion.TaskResult(),
                                isLoading = false
                            )
                        }
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                taskNumber = taskId,
                                taskResult = result.data ?: Task.Companion.TaskResult(),
                                isLoading = false
                            )
                            _eventFlow.emit(
                                UIEvent.ShowSnackbar(
                                    result.message ?: "Unknown error"
                                )
                            )
                        }
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                taskNumber = taskId,
                                taskResult = result.data ?: Task.Companion.TaskResult(),
                                isLoading = true
                            )
                        }
                    }
                }.launchIn(this)
        }
    }

    sealed class UIEvent {
        data class ShowSnackbar(val message: String): UIEvent()
    }
}