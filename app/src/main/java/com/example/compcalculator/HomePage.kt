package com.example.compcalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun HomePage(){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment =Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(text = "Successfully Logged In..", modifier = Modifier.background(Color.Cyan), fontSize = 50.sp)

    }
}