package com.example.sporti.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sporti.R
import com.example.sporti.data.SportsRepository
import com.example.sporti.data.RewardsRepository
import com.example.sporti.data.SportType
import com.example.sporti.data.WeightEntry

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    viewModel: MainViewModel,
    rewardsViewModel: RewardsViewModel,
    onStartWorkout: (String) -> Unit,
    onOpenRewards: () -> Unit
) {
    val weightEntries by viewModel.weightEntries.collectAsState()
    val totalWorkouts by rewardsViewModel.totalWorkouts.collectAsState()
    val totalPoints by rewardsViewModel.totalPoints.collectAsState()
    val currentStreak by rewardsViewModel.currentStreak.collectAsState()
    var showSheet by remember { mutableStateOf(false) }
    val sports = remember { SportsRepository.getAllSports() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Header
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .padding(top = 32.dp)
                ) {
                    Text(
                        text = stringResource(R.string.greeting),
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = stringResource(R.string.subtitle_ready),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Quick Stats Bar
            item {
                QuickStatsBar(
                    totalWorkouts = totalWorkouts,
                    latestWeight = weightEntries.firstOrNull()?.weight,
                    streak = currentStreak,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }

            // Rewards banner
            item {
                RewardsBanner(
                    totalPoints = totalPoints,
                    streak = currentStreak,
                    onClick = onOpenRewards,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }

            // Featured workout highlight
            item {
                FeaturedWorkoutCard(
                    sport = sports.first(),
                    onClick = { onStartWorkout(sports.first().id) },
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }

            // Section title
            item {
                Text(
                    text = stringResource(R.string.all_workouts),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }

            // Sports grid
            item {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(((sports.size + 1) / 2 * 140).dp)
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    userScrollEnabled = false
                ) {
                    items(sports) { sport ->
                        SportCard(
                            sport = sport,
                            onClick = { onStartWorkout(sport.id) }
                        )
                    }
                }
            }

            // Weight tracker
            item {
                WeightTrackerCard(
                    entries = weightEntries,
                    onAddClick = { showSheet = true },
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }

    if (showSheet) {
        WeightInputDialog(
            onDismiss = { showSheet = false },
            onSave = { weight ->
                viewModel.addWeight(weight)
                showSheet = false
            }
        )
    }
}

@Composable
fun QuickStatsBar(totalWorkouts: Int, latestWeight: Float?, streak: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        StatChip(
            label = stringResource(R.string.stat_workouts),
            value = "$totalWorkouts",
            color = Color(0xFF42A5F5),
            modifier = Modifier.weight(1f)
        )
        StatChip(
            label = stringResource(R.string.stat_weight),
            value = if (latestWeight != null) "${latestWeight}kg" else "--",
            color = Color(0xFF66BB6A),
            modifier = Modifier.weight(1f)
        )
        StatChip(
            label = stringResource(R.string.stat_streak),
            value = if (streak > 0) "$streak 🔥" else "0",
            color = Color(0xFFFF7043),
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun StatChip(label: String, value: String, color: Color, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = color.copy(alpha = 0.12f)
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = color
            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = color.copy(alpha = 0.8f)
            )
        }
    }
}

@Composable
fun FeaturedWorkoutCard(sport: SportType, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.97f else 1f,
        animationSpec = spring(stiffness = Spring.StiffnessHigh)
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp)
            .scale(scale)
            .clickable(interactionSource = interactionSource, indication = null) { onClick() },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(sport.color),
                            Color(sport.color).copy(alpha = 0.6f)
                        )
                    )
                )
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column {
                        Text(
                            text = stringResource(R.string.featured),
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.White.copy(alpha = 0.8f),
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = stringResource(sport.nameRes),
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.White,
                            fontWeight = FontWeight.Black
                        )
                    }
                    Text(
                        text = sport.icon,
                        fontSize = 48.sp
                    )
                }
                Text(
                    text = "${sport.exercises.size} ${stringResource(R.string.exercises_label)}  •  ${sport.exercises.sumOf { it.durationSeconds } / 60} ${stringResource(R.string.minutes)}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.9f),
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun SportCard(sport: SportType, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(stiffness = Spring.StiffnessHigh)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(128.dp)
            .scale(scale)
            .clickable(interactionSource = interactionSource, indication = null) { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Accent bar
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .fillMaxHeight()
                    .background(Color(sport.color))
                    .align(Alignment.CenterStart)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color(sport.color).copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = sport.icon, fontSize = 20.sp)
                    }
                }
                Column {
                    Text(
                        text = stringResource(sport.nameRes),
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "${sport.exercises.size} ${stringResource(R.string.exercises_label)}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun WeightTrackerCard(entries: List<WeightEntry>, onAddClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.weight_tracker),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = if (entries.isNotEmpty()) "${entries.first().weight} ${stringResource(R.string.kg)}" else "--",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Black
                    )
                }
                FilledIconButton(
                    onClick = onAddClick,
                    shape = CircleShape,
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Weight")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (entries.size > 1) {
                WeightChart(entries = entries.reversed(), modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth())
            } else {
                Text(
                    text = stringResource(R.string.weight_empty_hint),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                )
            }
        }
    }
}

@Composable
fun WeightChart(entries: List<WeightEntry>, modifier: Modifier = Modifier) {
    val primaryColor = MaterialTheme.colorScheme.primary
    Canvas(modifier = modifier) {
        if (entries.isEmpty()) return@Canvas

        val maxWeight = entries.maxOf { it.weight }
        val minWeight = entries.minOf { it.weight }
        val range = (maxWeight - minWeight).coerceAtLeast(1f)

        val width = size.width
        val height = size.height
        val hPadding = 8f
        val vPadding = 8f
        val usableWidth = width - 2 * hPadding
        val usableHeight = height - 2 * vPadding
        val spacing = usableWidth / (entries.size - 1).coerceAtLeast(1)

        val points = entries.mapIndexed { index, entry ->
            val x = hPadding + index * spacing
            val y = vPadding + usableHeight - ((entry.weight - minWeight) / range * usableHeight)
            Offset(x, y)
        }

        val path = Path().apply {
            moveTo(points.first().x, points.first().y)
            for (i in 1 until points.size) {
                val prev = points[i - 1]
                val cur = points[i]
                val cx = (prev.x + cur.x) / 2
                cubicTo(cx, prev.y, cx, cur.y, cur.x, cur.y)
            }
        }

        drawPath(
            path = path,
            color = primaryColor,
            style = Stroke(width = 3.dp.toPx())
        )

        // Draw dots
        points.forEach { point ->
            drawCircle(
                color = primaryColor,
                radius = 4.dp.toPx(),
                center = point
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeightInputDialog(onDismiss: () -> Unit, onSave: (Float) -> Unit) {
    var weightText by remember { mutableStateOf("") }
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.log_weight),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = weightText,
                onValueChange = { weightText = it },
                label = { Text(stringResource(R.string.kg)) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { weightText.toFloatOrNull()?.let { onSave(it) } },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.save),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun RewardsBanner(
    totalPoints: Int,
    streak: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val (level, levelName) = RewardsRepository.getLevel(totalPoints)
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.97f else 1f,
        animationSpec = spring(stiffness = Spring.StiffnessHigh)
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .scale(scale)
            .clickable(interactionSource = interactionSource, indication = null) { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        colors = listOf(Color(0xFF667EEA), Color(0xFF764BA2))
                    )
                )
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "🏆", fontSize = 32.sp)
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = stringResource(R.string.rewards_banner_title),
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = stringResource(R.string.level_label, level) + " • $totalPoints ${stringResource(R.string.points_short)}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }
                }
                Text(
                    text = "→",
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
