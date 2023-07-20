package com.example.compcalculator

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

interface DestinationsBottom{

    val route:String
    val icon: ImageVector
    val title:String

}

object homeBottom:DestinationsBottom{

    override val route="homeBottom"
    override val icon = Icons.Filled.Home
    override val title="Home"
}


object settingBottom:DestinationsBottom{

    override val route="SettingBottom"
    override val icon = Icons.Filled.Settings
    override val title="Setting"
}
