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
                Exercise(R.string.ex_neck_circles, 30, R.string.ex_desc_neck_circles, R.drawable.ic_ex_neck_circles),
                Exercise(R.string.ex_arm_circles, 30, R.string.ex_desc_arm_circles, R.drawable.ic_ex_arm_circles),
                Exercise(R.string.ex_jumping_jacks, 45, R.string.ex_desc_jumping_jacks, R.drawable.ic_ex_jumping_jacks),
                Exercise(R.string.ex_high_knees, 30, R.string.ex_desc_high_knees, R.drawable.ic_ex_high_knees),
                Exercise(R.string.ex_bodyweight_squats, 45, R.string.ex_desc_bodyweight_squats, R.drawable.ic_ex_squats),
                Exercise(R.string.ex_lunges, 40, R.string.ex_desc_lunges, R.drawable.ic_ex_lunges),
                Exercise(R.string.ex_cat_cow_stretch, 30, R.string.ex_desc_cat_cow, R.drawable.ic_ex_cat_cow),
            )
        ),
        SportType(
            id = "running",
            nameRes = R.string.sport_running,
            icon = "🏃",
            color = 0xFF42A5F5,
            exercises = listOf(
                Exercise(R.string.ex_warm_up_jog, 60, R.string.ex_desc_warm_up_jog, R.drawable.ic_ex_jog),
                Exercise(R.string.ex_high_knees, 30, R.string.ex_desc_high_knees, R.drawable.ic_ex_high_knees),
                Exercise(R.string.ex_butt_kicks, 30, R.string.ex_desc_butt_kicks, R.drawable.ic_ex_butt_kicks),
                Exercise(R.string.ex_sprint_intervals, 45, R.string.ex_desc_sprint_intervals, R.drawable.ic_ex_sprint),
                Exercise(R.string.ex_side_shuffle, 30, R.string.ex_desc_side_shuffle, R.drawable.ic_ex_side_shuffle),
                Exercise(R.string.ex_cool_down_walk, 60, R.string.ex_desc_cool_down_walk, R.drawable.ic_ex_cool_down_walk),
            )
        ),
        SportType(
            id = "yoga",
            nameRes = R.string.sport_yoga,
            icon = "🧘",
            color = 0xFFAB47BC,
            exercises = listOf(
                Exercise(R.string.ex_mountain_pose, 30, R.string.ex_desc_mountain_pose, R.drawable.ic_ex_mountain_pose),
                Exercise(R.string.ex_downward_dog, 45, R.string.ex_desc_downward_dog, R.drawable.ic_ex_downward_dog),
                Exercise(R.string.ex_warrior_one, 40, R.string.ex_desc_warrior_one, R.drawable.ic_ex_warrior),
                Exercise(R.string.ex_warrior_two, 40, R.string.ex_desc_warrior_two, R.drawable.ic_ex_warrior),
                Exercise(R.string.ex_tree_pose, 30, R.string.ex_desc_tree_pose, R.drawable.ic_ex_tree_pose),
                Exercise(R.string.ex_cobra_pose, 30, R.string.ex_desc_cobra_pose, R.drawable.ic_ex_cobra_pose),
                Exercise(R.string.ex_child_pose, 45, R.string.ex_desc_child_pose, R.drawable.ic_ex_child_pose),
                Exercise(R.string.ex_savasana, 60, R.string.ex_desc_savasana, R.drawable.ic_ex_savasana),
            )
        ),
        SportType(
            id = "hiit",
            nameRes = R.string.sport_hiit,
            icon = "🔥",
            color = 0xFFEF5350,
            exercises = listOf(
                Exercise(R.string.ex_burpees, 30, R.string.ex_desc_burpees, R.drawable.ic_ex_burpees),
                Exercise(R.string.ex_mountain_climbers, 30, R.string.ex_desc_mountain_climbers, R.drawable.ic_ex_mountain_climbers),
                Exercise(R.string.ex_jump_squats, 30, R.string.ex_desc_jump_squats, R.drawable.ic_ex_jump_squats),
                Exercise(R.string.ex_push_ups, 30, R.string.ex_desc_push_ups, R.drawable.ic_ex_push_ups),
                Exercise(R.string.ex_high_knees, 30, R.string.ex_desc_high_knees, R.drawable.ic_ex_high_knees),
                Exercise(R.string.ex_plank_jacks, 30, R.string.ex_desc_plank_jacks, R.drawable.ic_ex_plank_jacks),
                Exercise(R.string.ex_tuck_jumps, 25, R.string.ex_desc_tuck_jumps, R.drawable.ic_ex_tuck_jumps),
                Exercise(R.string.ex_bicycle_crunches, 30, R.string.ex_desc_bicycle_crunches, R.drawable.ic_ex_bicycle_crunches),
            )
        ),
        SportType(
            id = "strength",
            nameRes = R.string.sport_strength,
            icon = "💪",
            color = 0xFF66BB6A,
            exercises = listOf(
                Exercise(R.string.ex_push_ups, 45, R.string.ex_desc_push_ups, R.drawable.ic_ex_push_ups),
                Exercise(R.string.ex_bodyweight_squats, 45, R.string.ex_desc_bodyweight_squats, R.drawable.ic_ex_squats),
                Exercise(R.string.ex_lunges, 40, R.string.ex_desc_lunges, R.drawable.ic_ex_lunges),
                Exercise(R.string.ex_plank, 60, R.string.ex_desc_plank, R.drawable.ic_ex_plank),
                Exercise(R.string.ex_tricep_dips, 30, R.string.ex_desc_tricep_dips, R.drawable.ic_ex_tricep_dips),
                Exercise(R.string.ex_glute_bridge, 40, R.string.ex_desc_glute_bridge, R.drawable.ic_ex_glute_bridge),
                Exercise(R.string.ex_superman, 30, R.string.ex_desc_superman, R.drawable.ic_ex_superman),
                Exercise(R.string.ex_wall_sit, 45, R.string.ex_desc_wall_sit, R.drawable.ic_ex_wall_sit),
            )
        ),
        SportType(
            id = "cardio",
            nameRes = R.string.sport_cardio,
            icon = "❤️",
            color = 0xFFEC407A,
            exercises = listOf(
                Exercise(R.string.ex_jumping_jacks, 45, R.string.ex_desc_jumping_jacks, R.drawable.ic_ex_jumping_jacks),
                Exercise(R.string.ex_high_knees, 30, R.string.ex_desc_high_knees, R.drawable.ic_ex_high_knees),
                Exercise(R.string.ex_butt_kicks, 30, R.string.ex_desc_butt_kicks, R.drawable.ic_ex_butt_kicks),
                Exercise(R.string.ex_skaters, 30, R.string.ex_desc_skaters, R.drawable.ic_ex_skaters),
                Exercise(R.string.ex_star_jumps, 30, R.string.ex_desc_star_jumps, R.drawable.ic_ex_star_jumps),
                Exercise(R.string.ex_box_step_ups, 45, R.string.ex_desc_box_step_ups, R.drawable.ic_ex_box_step_ups),
                Exercise(R.string.ex_speed_skipping, 45, R.string.ex_desc_speed_skipping, R.drawable.ic_ex_speed_skipping),
            )
        ),
        SportType(
            id = "boxing",
            nameRes = R.string.sport_boxing,
            icon = "🥊",
            color = 0xFFFF7043,
            exercises = listOf(
                Exercise(R.string.ex_jab_cross, 30, R.string.ex_desc_jab_cross, R.drawable.ic_ex_jab_cross),
                Exercise(R.string.ex_hook_uppercut, 30, R.string.ex_desc_hook_uppercut, R.drawable.ic_ex_hook_uppercut),
                Exercise(R.string.ex_bob_weave, 30, R.string.ex_desc_bob_weave, R.drawable.ic_ex_bob_weave),
                Exercise(R.string.ex_shadow_boxing, 60, R.string.ex_desc_shadow_boxing, R.drawable.ic_ex_shadow_boxing),
                Exercise(R.string.ex_boxing_footwork, 30, R.string.ex_desc_boxing_footwork, R.drawable.ic_ex_boxing_footwork),
                Exercise(R.string.ex_speed_punches, 30, R.string.ex_desc_speed_punches, R.drawable.ic_ex_speed_punches),
                Exercise(R.string.ex_defensive_drills, 30, R.string.ex_desc_defensive_drills, R.drawable.ic_ex_defensive_drills),
            )
        ),
        SportType(
            id = "flexibility",
            nameRes = R.string.sport_flexibility,
            icon = "🤸",
            color = 0xFF26C6DA,
            exercises = listOf(
                Exercise(R.string.ex_hamstring_stretch, 40, R.string.ex_desc_hamstring_stretch, R.drawable.ic_ex_hamstring_stretch),
                Exercise(R.string.ex_quad_stretch, 30, R.string.ex_desc_quad_stretch, R.drawable.ic_ex_quad_stretch),
                Exercise(R.string.ex_hip_flexor_stretch, 40, R.string.ex_desc_hip_flexor, R.drawable.ic_ex_hip_flexor),
                Exercise(R.string.ex_shoulder_stretch, 30, R.string.ex_desc_shoulder_stretch, R.drawable.ic_ex_shoulder_stretch),
                Exercise(R.string.ex_cat_cow_stretch, 30, R.string.ex_desc_cat_cow, R.drawable.ic_ex_cat_cow),
                Exercise(R.string.ex_seated_forward_fold, 45, R.string.ex_desc_seated_forward_fold, R.drawable.ic_ex_seated_forward_fold),
                Exercise(R.string.ex_pigeon_pose, 40, R.string.ex_desc_pigeon_pose, R.drawable.ic_ex_pigeon_pose),
            )
        ),
        SportType(
            id = "core",
            nameRes = R.string.sport_core,
            icon = "🎯",
            color = 0xFFFFA726,
            exercises = listOf(
                Exercise(R.string.ex_plank, 60, R.string.ex_desc_plank, R.drawable.ic_ex_plank),
                Exercise(R.string.ex_bicycle_crunches, 30, R.string.ex_desc_bicycle_crunches, R.drawable.ic_ex_bicycle_crunches),
                Exercise(R.string.ex_russian_twists, 30, R.string.ex_desc_russian_twists, R.drawable.ic_ex_russian_twists),
                Exercise(R.string.ex_leg_raises, 30, R.string.ex_desc_leg_raises, R.drawable.ic_ex_leg_raises),
                Exercise(R.string.ex_dead_bug, 30, R.string.ex_desc_dead_bug, R.drawable.ic_ex_dead_bug),
                Exercise(R.string.ex_side_plank_left, 30, R.string.ex_desc_side_plank, R.drawable.ic_ex_side_plank),
                Exercise(R.string.ex_side_plank_right, 30, R.string.ex_desc_side_plank, R.drawable.ic_ex_side_plank),
                Exercise(R.string.ex_flutter_kicks, 30, R.string.ex_desc_flutter_kicks, R.drawable.ic_ex_flutter_kicks),
            )
        ),
        SportType(
            id = "evening",
            nameRes = R.string.evening_stretch,
            icon = "🌙",
            color = 0xFF7E57C2,
            exercises = listOf(
                Exercise(R.string.ex_neck_circles, 30, R.string.ex_desc_neck_circles, R.drawable.ic_ex_neck_circles),
                Exercise(R.string.ex_shoulder_stretch, 30, R.string.ex_desc_shoulder_stretch, R.drawable.ic_ex_shoulder_stretch),
                Exercise(R.string.ex_seated_forward_fold, 45, R.string.ex_desc_seated_forward_fold, R.drawable.ic_ex_seated_forward_fold),
                Exercise(R.string.ex_hamstring_stretch, 40, R.string.ex_desc_hamstring_stretch, R.drawable.ic_ex_hamstring_stretch),
                Exercise(R.string.ex_child_pose, 60, R.string.ex_desc_child_pose, R.drawable.ic_ex_child_pose),
                Exercise(R.string.ex_savasana, 90, R.string.ex_desc_savasana, R.drawable.ic_ex_savasana),
            )
        ),
        SportType(
            id = "cycling",
            nameRes = R.string.sport_cycling,
            icon = "🚴",
            color = 0xFF5C6BC0,
            exercises = listOf(
                Exercise(R.string.ex_warm_up_ride, 60, R.string.ex_desc_warm_up_ride, R.drawable.ic_ex_cycling),
                Exercise(R.string.ex_hill_climb_sim, 45, R.string.ex_desc_hill_climb, R.drawable.ic_ex_cycling),
                Exercise(R.string.ex_sprint_intervals, 30, R.string.ex_desc_sprint_intervals, R.drawable.ic_ex_sprint),
                Exercise(R.string.ex_endurance_ride, 90, R.string.ex_desc_endurance_ride, R.drawable.ic_ex_cycling),
                Exercise(R.string.ex_cool_down_ride, 60, R.string.ex_desc_cool_down_ride, R.drawable.ic_ex_cycling),
            )
        ),
        SportType(
            id = "swimming",
            nameRes = R.string.sport_swimming,
            icon = "🏊",
            color = 0xFF29B6F6,
            exercises = listOf(
                Exercise(R.string.ex_arm_circles, 30, R.string.ex_desc_arm_circles, R.drawable.ic_ex_arm_circles),
                Exercise(R.string.ex_freestyle_drills, 60, R.string.ex_desc_freestyle_drills, R.drawable.ic_ex_swimming),
                Exercise(R.string.ex_backstroke_drills, 60, R.string.ex_desc_backstroke_drills, R.drawable.ic_ex_swimming),
                Exercise(R.string.ex_treading_water, 45, R.string.ex_desc_treading_water, R.drawable.ic_ex_swimming),
                Exercise(R.string.ex_flutter_kicks, 30, R.string.ex_desc_flutter_kicks, R.drawable.ic_ex_flutter_kicks),
                Exercise(R.string.ex_cool_down_swim, 60, R.string.ex_desc_cool_down_swim, R.drawable.ic_ex_swimming),
            )
        ),
    )

    fun getSportById(id: String): SportType? = getAllSports().find { it.id == id }
}
