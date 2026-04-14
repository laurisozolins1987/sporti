package com.example.sporti.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.example.sporti.data.Achievement
import com.example.sporti.data.AchievementType
import com.example.sporti.data.RewardsRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RewardsScreen(
    rewardsViewModel: RewardsViewModel,
    onBack: () -> Unit
) {
    val totalWorkouts by rewardsViewModel.totalWorkouts.collectAsState()
    val totalPoints by rewardsViewModel.totalPoints.collectAsState()
    val currentStreak by rewardsViewModel.currentStreak.collectAsState()
    val uniqueSports by rewardsViewModel.uniqueSports.collectAsState()

    val (level, levelName) = RewardsRepository.getLevel(totalPoints)
    val nextLevelPoints = RewardsRepository.getPointsForNextLevel(totalPoints)
    val levelProgress = if (nextLevelPoints > 0) totalPoints.toFloat() / nextLevelPoints else 1f
    val achievements = remember { RewardsRepository.getAllAchievements() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.rewards_title),
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Level card
            item {
                LevelCard(
                    level = level,
                    levelName = levelName,
                    totalPoints = totalPoints,
                    nextLevelPoints = nextLevelPoints,
                    progress = levelProgress,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }

            // Stats row
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    RewardStatCard(
                        icon = "🏋️",
                        value = "$totalWorkouts",
                        label = stringResource(R.string.total_workouts_label),
                        color = Color(0xFF42A5F5),
                        modifier = Modifier.weight(1f)
                    )
                    RewardStatCard(
                        icon = "🔥",
                        value = "$currentStreak",
                        label = stringResource(R.string.day_streak_label),
                        color = Color(0xFFFF7043),
                        modifier = Modifier.weight(1f)
                    )
                    RewardStatCard(
                        icon = "⭐",
                        value = "$totalPoints",
                        label = stringResource(R.string.points_label),
                        color = Color(0xFFFFA726),
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Streak bonus info
            if (currentStreak > 0) {
                item {
                    StreakBonusCard(
                        streak = currentStreak,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                }
            }

            // Achievements header
            item {
                Text(
                    text = stringResource(R.string.achievements_title),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp)
                )
            }

            // Achievement items
            items(achievements) { achievement ->
                val currentValue = when (achievement.type) {
                    AchievementType.TOTAL_WORKOUTS -> totalWorkouts
                    AchievementType.TOTAL_POINTS -> totalPoints
                    AchievementType.STREAK_DAYS -> currentStreak
                    AchievementType.UNIQUE_SPORTS -> uniqueSports
                    AchievementType.SINGLE_SPORT_COUNT -> 0
                }
                AchievementCard(
                    achievement = achievement,
                    currentValue = currentValue,
                    isUnlocked = currentValue >= achievement.targetValue,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun LevelCard(
    level: Int,
    levelName: String,
    totalPoints: Int,
    nextLevelPoints: Int,
    progress: Float,
    modifier: Modifier = Modifier
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress.coerceIn(0f, 1f),
        animationSpec = tween(1000, easing = FastOutSlowInEasing)
    )

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF667EEA),
                            Color(0xFF764BA2)
                        )
                    )
                )
                .padding(24.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Level badge
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "$level",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(RewardsRepository.getLevelTitleRes(levelName)),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = stringResource(R.string.level_label, level),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.8f)
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Progress bar
                LinearProgressIndicator(
                    progress = { animatedProgress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = Color(0xFF00E676),
                    trackColor = Color.White.copy(alpha = 0.2f)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "$totalPoints / $nextLevelPoints ${stringResource(R.string.points_short)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }
    }
}

@Composable
fun RewardStatCard(
    icon: String,
    value: String,
    label: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = icon, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Black,
                color = color
            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun StreakBonusCard(streak: Int, modifier: Modifier = Modifier) {
    val bonus = streak * RewardsRepository.STREAK_BONUS_MULTIPLIER
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFF7043).copy(alpha = 0.12f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "🔥", fontSize = 28.sp)
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = stringResource(R.string.streak_bonus_title),
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFF7043)
                    )
                    Text(
                        text = stringResource(R.string.streak_bonus_desc, bonus),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Text(
                text = "+$bonus",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Black,
                color = Color(0xFFFF7043)
            )
        }
    }
}

@Composable
fun AchievementCard(
    achievement: Achievement,
    currentValue: Int,
    isUnlocked: Boolean,
    modifier: Modifier = Modifier
) {
    val progress = (currentValue.toFloat() / achievement.targetValue).coerceIn(0f, 1f)
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(800, easing = FastOutSlowInEasing)
    )
    val accentColor = if (isUnlocked) Color(achievement.color) else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isUnlocked)
                Color(achievement.color).copy(alpha = 0.08f)
            else
                MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(
                        if (isUnlocked) Color(achievement.color).copy(alpha = 0.15f)
                        else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (isUnlocked) achievement.icon else "🔒",
                    fontSize = 22.sp
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(achievement.titleRes),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = if (isUnlocked)
                        MaterialTheme.colorScheme.onSurface
                    else
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = stringResource(achievement.descriptionRes),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Progress bar
                LinearProgressIndicator(
                    progress = { animatedProgress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp)),
                    color = accentColor,
                    trackColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "$currentValue / ${achievement.targetValue}",
                    style = MaterialTheme.typography.labelSmall,
                    color = accentColor
                )
            }

            if (isUnlocked) {
                Text(text = "✅", fontSize = 20.sp)
            }
        }
    }
}
