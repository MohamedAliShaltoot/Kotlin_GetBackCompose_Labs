package com.example.kotlin_getback_compose_lab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.kotlin_getback_compose_lab1.data_class.Screens
import com.example.kotlin_getback_compose_lab1.last_lab.screens.HomeScreen
import com.example.kotlin_getback_compose_lab1.last_lab.screens.LoginScreen
import com.example.kotlin_getback_compose_lab1.last_lab.screens.SignUpScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {

    val con = rememberNavController()

    NavHost(
        navController = con,
        startDestination = Screens.SignUp()
    ) {

        composable<Screens.SignUp> {
            SignUpScreen(con)
        }

        composable<Screens.Login> { backStackEntry ->
            val screen = backStackEntry.toRoute<Screens.Login>()
            LoginScreen(
                correctName = screen.name,
                correctPass = screen.password,
                navController = con
            )
        }

        composable<Screens.Home> { backStackEntry ->
            val screen = backStackEntry.toRoute<Screens.Home>()
            HomeScreen(screen.username, con)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPrev() {
    MyApp()
}



