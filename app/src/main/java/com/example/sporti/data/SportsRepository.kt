package com.example.sporti.data

import com.example.sporti.R

object SportsRepository {

    fun getAllSports(): List<SportType> = listOf(
        SportType(
            id = "morning",
            nameRes = R.string.morning_routine,
            icon = "☀️",
            color = 0xFFFFB74D,
            exercises = listOf(
                Exercise(R.string.ex_neck_circles, 30),
                Exercise(R.string.ex_arm_circles, 30),
                Exercise(R.string.ex_jumping_jacks, 45),
                Exercise(R.string.ex_high_knees, 30),
                Exercise(R.string.ex_bodyweight_squats, 45),
                Exercise(R.string.ex_lunges, 40),
                Exercise(R.string.ex_cat_cow_stretch, 30),
            )
        ),
        SportType(
            id = "running",
            nameRes = R.string.sport_running,
            icon = "🏃",
            color = 0xFF42A5F5,
            exercises = listOf(
                Exercise(R.string.ex_warm_up_jog, 60),
                Exercise(R.string.ex_high_knees, 30),
                Exercise(R.string.ex_butt_kicks, 30),
                Exercise(R.string.ex_sprint_intervals, 45),
                Exercise(R.string.ex_side_shuffle, 30),
                Exercise(R.string.ex_cool_down_walk, 60),
            )
        ),
        SportType(
            id = "yoga",
            nameRes = R.string.sport_yoga,
            icon = "🧘",
            color = 0xFFAB47BC,
            exercises = listOf(
                Exercise(R.string.ex_mountain_pose, 30),
                Exercise(R.string.ex_downward_dog, 45),
                Exercise(R.string.ex_warrior_one, 40),
                Exercise(R.string.ex_warrior_two, 40),
                Exercise(R.string.ex_tree_pose, 30),
                Exercise(R.string.ex_cobra_pose, 30),
                Exercise(R.string.ex_child_pose, 45),
                Exercise(R.string.ex_savasana, 60),
            )
        ),
        SportType(
            id = "hiit",
            nameRes = R.string.sport_hiit,
            icon = "🔥",
            color = 0xFFEF5350,
            exercises = listOf(
                Exercise(R.string.ex_burpees, 30),
                Exercise(R.string.ex_mountain_climbers, 30),
                Exercise(R.string.ex_jump_squats, 30),
                Exercise(R.string.ex_push_ups, 30),
                Exercise(R.string.ex_high_knees, 30),
                Exercise(R.string.ex_plank_jacks, 30),
                Exercise(R.string.ex_tuck_jumps, 25),
                Exercise(R.string.ex_bicycle_crunches, 30),
            )
        ),
        SportType(
            id = "strength",
            nameRes = R.string.sport_strength,
            icon = "💪",
            color = 0xFF66BB6A,
            exercises = listOf(
                Exercise(R.string.ex_push_ups, 45),
                Exercise(R.string.ex_bodyweight_squats, 45),
                Exercise(R.string.ex_lunges, 40),
                Exercise(R.string.ex_plank, 60),
                Exercise(R.string.ex_tricep_dips, 30),
                Exercise(R.string.ex_glute_bridge, 40),
                Exercise(R.string.ex_superman, 30),
                Exercise(R.string.ex_wall_sit, 45),
            )
        ),
        SportType(
            id = "cardio",
            nameRes = R.string.sport_cardio,
            icon = "❤️",
            color = 0xFFEC407A,
            exercises = listOf(
                Exercise(R.string.ex_jumping_jacks, 45),
                Exercise(R.string.ex_high_knees, 30),
                Exercise(R.string.ex_butt_kicks, 30),
                Exercise(R.string.ex_skaters, 30),
                Exercise(R.string.ex_star_jumps, 30),
                Exercise(R.string.ex_box_step_ups, 45),
                Exercise(R.string.ex_speed_skipping, 45),
            )
        ),
        SportType(
            id = "boxing",
            nameRes = R.string.sport_boxing,
            icon = "🥊",
            color = 0xFFFF7043,
            exercises = listOf(
                Exercise(R.string.ex_jab_cross, 30),
                Exercise(R.string.ex_hook_uppercut, 30),
                Exercise(R.string.ex_bob_weave, 30),
                Exercise(R.string.ex_shadow_boxing, 60),
                Exercise(R.string.ex_boxing_footwork, 30),
                Exercise(R.string.ex_speed_punches, 30),
                Exercise(R.string.ex_defensive_drills, 30),
            )
        ),
        SportType(
            id = "flexibility",
            nameRes = R.string.sport_flexibility,
            icon = "🤸",
            color = 0xFF26C6DA,
            exercises = listOf(
                Exercise(R.string.ex_hamstring_stretch, 40),
                Exercise(R.string.ex_quad_stretch, 30),
                Exercise(R.string.ex_hip_flexor_stretch, 40),
                Exercise(R.string.ex_shoulder_stretch, 30),
                Exercise(R.string.ex_cat_cow_stretch, 30),
                Exercise(R.string.ex_seated_forward_fold, 45),
                Exercise(R.string.ex_pigeon_pose, 40),
            )
        ),
        SportType(
            id = "core",
            nameRes = R.string.sport_core,
            icon = "🎯",
            color = 0xFFFFA726,
            exercises = listOf(
                Exercise(R.string.ex_plank, 60),
                Exercise(R.string.ex_bicycle_crunches, 30),
                Exercise(R.string.ex_russian_twists, 30),
                Exercise(R.string.ex_leg_raises, 30),
                Exercise(R.string.ex_dead_bug, 30),
                Exercise(R.string.ex_side_plank_left, 30),
                Exercise(R.string.ex_side_plank_right, 30),
                Exercise(R.string.ex_flutter_kicks, 30),
            )
        ),
        SportType(
            id = "evening",
            nameRes = R.string.evening_stretch,
            icon = "🌙",
            color = 0xFF7E57C2,
            exercises = listOf(
                Exercise(R.string.ex_neck_circles, 30),
                Exercise(R.string.ex_shoulder_stretch, 30),
                Exercise(R.string.ex_seated_forward_fold, 45),
                Exercise(R.string.ex_hamstring_stretch, 40),
                Exercise(R.string.ex_child_pose, 60),
                Exercise(R.string.ex_savasana, 90),
            )
        ),
        SportType(
            id = "cycling",
            nameRes = R.string.sport_cycling,
            icon = "🚴",
            color = 0xFF5C6BC0,
            exercises = listOf(
                Exercise(R.string.ex_warm_up_ride, 60),
                Exercise(R.string.ex_hill_climb_sim, 45),
                Exercise(R.string.ex_sprint_intervals, 30),
                Exercise(R.string.ex_endurance_ride, 90),
                Exercise(R.string.ex_cool_down_ride, 60),
            )
        ),
        SportType(
            id = "swimming",
            nameRes = R.string.sport_swimming,
            icon = "🏊",
            color = 0xFF29B6F6,
            exercises = listOf(
                Exercise(R.string.ex_arm_circles, 30),
                Exercise(R.string.ex_freestyle_drills, 60),
                Exercise(R.string.ex_backstroke_drills, 60),
                Exercise(R.string.ex_treading_water, 45),
                Exercise(R.string.ex_flutter_kicks, 30),
                Exercise(R.string.ex_cool_down_swim, 60),
            )
        ),
    )

    fun getSportById(id: String): SportType? = getAllSports().find { it.id == id }
}
