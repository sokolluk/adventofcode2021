package cz.sokolluk.adventofcode2021.feature_tasks.presentation.util

sealed class Screen(val route: String) {
    object TasksScreen: Screen("tasks_screen")
    object TaskDetailScreen: Screen("task_detail_screen") {
        fun createRoute(taskId: Int): String {
            return "$route?taskId=$taskId"
        }
    }
}