package com.example.compcalculator

interface Destinations {
    val route:String
}

object LogIn:Destinations{
    override val route="LogIn"
}

object Dashboard:Destinations{
    override val route="Dashboard"
}

