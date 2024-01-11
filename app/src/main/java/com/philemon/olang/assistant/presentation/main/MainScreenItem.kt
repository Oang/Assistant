package com.philemon.olang.assistant.presentation.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.philemon.olang.assistant.domain.model.MessageSection

data class MainScreenItem(
    @StringRes val title: Int,
    @StringRes val description: Int,
    val route: String,
    @DrawableRes val icon: Int,
    val section: MessageSection
)


