package com.example.seajudge.ui.feature.onboarding

import com.example.seajudge.R

sealed class OnboardingItem(
    val title: String,
    val description: String,
    val image: Int
) {
    object OnboardingScreenOne : OnboardingItem(
        title = "Sea Judge",
        description = "Aplikasi yang  mempermudah pelaporan pelanggaran hukum di laut",
        image = R.drawable.img_onboarding_one
    )

    object OnboardingScreenTwo : OnboardingItem(
        title = "Laporan Aman",
        description = "Laporan akan kami simpan di tempat yang aman dan akan diberikan ke pada pihak yang berwenang",
        image = R.drawable.img_onboarding_two
    )

    object OnboardingScreenThree : OnboardingItem(
        title = "",
        description = "",
        image = R.drawable.img_onboarding_three
    )
}
