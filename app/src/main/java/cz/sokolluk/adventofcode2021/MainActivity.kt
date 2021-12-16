package cz.sokolluk.adventofcode2021

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cz.sokolluk.adventofcode2021.feature_tasks.presentation.task_detail.TaskDetailScreen
import cz.sokolluk.adventofcode2021.feature_tasks.presentation.task_list.TasksScreen
import cz.sokolluk.adventofcode2021.feature_tasks.presentation.util.Screen
import cz.sokolluk.adventofcode2021.ui.theme.AdventOfCode2021Theme
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdventOfCode2021Theme {
                Surface {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.TasksScreen.route
                    ) {
                        composable(route = Screen.TasksScreen.route) {
                            TasksScreen(navController = navController)
                        }
                        composable(
                            route = Screen.TaskDetailScreen.route +
                                    "?taskId={taskId}",
                            arguments = listOf(
                                navArgument(
                                    name = "taskId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ) {
                            TaskDetailScreen(
                                navController = navController
                            )
                        }
                    }
                }

            }
        }
    }
}