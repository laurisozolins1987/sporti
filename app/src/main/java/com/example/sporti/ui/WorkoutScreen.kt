package com.example.sporti.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sporti.R
import com.example.sporti.data.SportsRepository
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutScreen(
    workoutType: String,
    onBack: () -> Unit
) {
    val sport = remember { SportsRepository.getSportById(workoutType) }
    val exercises = sport?.exercises ?: emptyList()
    val sportColor = Color(sport?.color ?: 0xFF00E676)

    var currentExerciseIndex by remember { mutableIntStateOf(0) }
    val currentExercise = exercises.getOrNull(currentExerciseIndex)
    val totalDuration = currentExercise?.durationSeconds ?: 30
    var timeLeft by remember(currentExerciseIndex) { mutableLongStateOf(totalDuration.toLong()) }
    var isRunning by remember { mutableStateOf(false) }
    var isCompleted by remember { mutableStateOf(false) }

    val progress by animateFloatAsState(
        targetValue = if (totalDuration > 0) timeLeft.toFloat() / totalDuration else 0f,
        animationSpec = tween(300)
    )

    LaunchedEffect(isRunning, timeLeft) {
        if (isRunning && timeLeft > 0) {
            delay(1000L)
            timeLeft--
        } else if (isRunning && timeLeft == 0L) {
            isRunning = false
            if (currentExerciseIndex < exercises.size - 1) {
                currentExerciseIndex++
            } else {
                isCompleted = true
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(sport?.nameRes ?: R.string.free_workout),
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.onBackground,
                    navigationIconContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        if (isCompleted) {
            // Completed state
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(sportColor.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = null,
                        tint = sportColor,
                        modifier = Modifier.size(48.dp)
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = stringResource(R.string.workout_complete),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.great_job),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = onBack,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = sportColor)
                ) {
                    Text(
                        text = stringResource(R.string.back_to_home),
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Progress indicator
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    exercises.forEachIndexed { index, _ ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(4.dp)
                                .clip(RoundedCornerShape(2.dp))
                                .background(
                                    if (index < currentExerciseIndex) sportColor
                                    else if (index == currentExerciseIndex) sportColor.copy(alpha = 0.5f)
                                    else MaterialTheme.colorScheme.outline
                                )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.exercise_counter, currentExerciseIndex + 1, exercises.size),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.weight(0.5f))

                // Sport icon
                Text(
                    text = sport?.icon ?: "🏋️",
                    fontSize = 48.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Exercise name with animation
                AnimatedContent(
                    targetState = currentExerciseIndex,
                    transitionSpec = {
                        (fadeIn() + slideInVertically { it }).togetherWith(
                            fadeOut() + slideOutVertically { -it }
                        )
                    }
                ) { index ->
                    Text(
                        text = if (exercises.isNotEmpty()) stringResource(exercises[index].nameRes) else "",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Black,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))

                // Timer circle
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        progress = { progress },
                        modifier = Modifier.size(200.dp),
                        color = sportColor,
                        trackColor = sportColor.copy(alpha = 0.12f),
                        strokeWidth = 8.dp
                    )
                    Text(
                        text = timeLeft.toString(),
                        fontSize = 72.sp,
                        fontWeight = FontWeight.Black,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                // Controls
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Reset button
                    if (!isRunning && timeLeft < totalDuration) {
                        OutlinedButton(
                            onClick = { timeLeft = totalDuration.toLong(); isRunning = false },
                            modifier = Modifier
                                .height(56.dp)
                                .weight(1f),
                            shape = RoundedCornerShape(16.dp),
                            border = ButtonDefaults.outlinedButtonBorder(true)
                        ) {
                            Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(stringResource(R.string.reset), fontWeight = FontWeight.Bold)
                        }
                    }

                    // Start/Pause button
                    Button(
                        onClick = { isRunning = !isRunning },
                        modifier = Modifier
                            .height(56.dp)
                            .then(
                                if (!isRunning && timeLeft >= totalDuration) Modifier.fillMaxWidth()
                                else Modifier.weight(1f)
                            ),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = sportColor,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = if (isRunning) stringResource(R.string.pause) else stringResource(R.string.start),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                // Skip button
                if (currentExerciseIndex < exercises.size - 1) {
                    TextButton(
                        onClick = {
                            isRunning = false
                            currentExerciseIndex++
                        },
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.skip_exercise),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
