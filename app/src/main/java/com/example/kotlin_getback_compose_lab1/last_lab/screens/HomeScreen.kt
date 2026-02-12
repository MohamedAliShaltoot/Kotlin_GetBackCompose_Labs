package com.example.kotlin_getback_compose_lab1.last_lab.screens
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kotlin_getback_compose_lab1.data_class.Screens
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(username: String, navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("HomeScreen") },
                actions = {
                    Text(text = username, color = Color.Red, fontSize = 18.sp)
                }

            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Hi, $username", fontSize = 26.sp)

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = {
                    navController.navigate(Screens.SignUp()) {
                        popUpTo(0)
                    }
                }, colors = ButtonDefaults.buttonColors(Color.Red),
                modifier = Modifier.width(350.dp).height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Log Out",fontSize = 30.sp)
            }
        }
    }
}
