package com.example.sporti

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sporti.ui.DashboardScreen
import com.example.sporti.ui.MainViewModel
import com.example.sporti.ui.RewardsScreen
import com.example.sporti.ui.RewardsViewModel
import com.example.sporti.ui.WorkoutScreen
import com.example.sporti.ui.theme.SportinsTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val rewardsViewModel: RewardsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SportinsTheme {
                SportiApp(viewModel, rewardsViewModel)
            }
        }
    }
}

@Composable
fun SportiApp(viewModel: MainViewModel, rewardsViewModel: RewardsViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "dashboard") {
        composable("dashboard") {
            DashboardScreen(
                viewModel = viewModel,
                rewardsViewModel = rewardsViewModel,
                onStartWorkout = { workoutType ->
                    navController.navigate("workout/$workoutType")
                },
                onOpenRewards = {
                    navController.navigate("rewards")
                }
            )
        }
        composable("workout/{workoutType}") { backStackEntry ->
            val workoutType = backStackEntry.arguments?.getString("workoutType") ?: "free"
            WorkoutScreen(
                workoutType = workoutType,
                rewardsViewModel = rewardsViewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable("rewards") {
            RewardsScreen(
                rewardsViewModel = rewardsViewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
