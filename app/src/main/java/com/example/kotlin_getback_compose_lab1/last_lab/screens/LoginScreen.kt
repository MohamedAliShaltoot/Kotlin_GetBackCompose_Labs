package com.example.kotlin_getback_compose_lab1.last_lab.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kotlin_getback_compose_lab1.data_class.Screens

@Composable
fun LoginScreen(
    correctName: String,
    correctPass: String,
    navController: NavController
) {

    var name by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text("Login", fontSize = 26.sp)

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(Modifier.height(12.dp))

//        OutlinedTextField(
//            value = pass,
//            onValueChange = { pass = it },
//            label = { Text("Password") },
//            modifier = Modifier.fillMaxWidth(),
//            shape = RoundedCornerShape(12.dp)
//        )
        OutlinedTextField(
            value = pass,
            onValueChange = { pass = it },
            label = { Text("Password") },
            isError = pass.isNotEmpty() && !isValidPassword(pass),
            supportingText = {
                if (pass.isNotEmpty() && !isValidPassword(pass)) {
                    Text("Minimum 8 characters")
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )


        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                when {
                    name.isBlank() || pass.isBlank() ->
                        error = "All fields required"

                    !isValidPassword(pass) ->
                        error = "Password must be at least 8 characters"

                    name == correctName && pass == correctPass -> {
                        error = ""
                        navController.navigate(
                            Screens.Home(name)
                        )
                    }

                    else ->
                        error = "Wrong credentials"
                }
            }, colors = ButtonDefaults.buttonColors(Color.Green),
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Login",fontSize = 30.sp)
        }

        Text(error, color = Color.Red)
    }
}
