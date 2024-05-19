package com.example.scaffold

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scaffoldtp3.AffirmationsApp
import com.example.scaffoldtp3.R
import com.example.scaffoldtp3.WoofApp
import com.example.scaffoldtp3.conv
import com.example.scaffoldtp3.data.Massage
import com.example.scaffoldtp3.data.msglist
import com.example.scaffoldtp3.ui.theme.ScaffoldTP3Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ScaffoldTP3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LayoutDesign(navController) // Pass currentDestination as state
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutDesign(navController: NavController) {

    val drawerState= rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope= rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState =drawerState ,
        drawerContent = { ModalDrawerSheet {
            Column (
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(1f)
                    .background(MaterialTheme.colorScheme.tertiary)
            ){
                Row (){

                    Image(
                        painter = painterResource(id = R.drawable.picture), // Replace with your image resource ID
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(70.dp)
                            .clip(CircleShape)
                            .padding(top = 10.dp, bottom = 10.dp)
                    )
                    Spacer(modifier= Modifier.height(10.dp))
                    Text(
                        text = "Mohamed louati",
                        color = Color.White,
                        fontSize = 30.sp
                    )
                }
            }

            Spacer(modifier= Modifier.height(10.dp))
            NavigationDrawerItem(
                label = { Text("Message Conversation") },
                selected = true,
                onClick = {navController.navigate("messages")

            }
            )
            Spacer(modifier= Modifier.height(10.dp))
            NavigationDrawerItem(
                label = { Text("Affirmations") },
                selected = true,
                onClick = {
                    navController.navigate("affirmations")

            })
            Spacer(modifier= Modifier.height(10.dp))
            NavigationDrawerItem(label = { Text("Woof") },
                selected = true,
                onClick = {
                    navController.navigate("woof")

            })
        }})
    {

        Scaffold(
            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        titleContentColor = MaterialTheme.colorScheme.onSecondary,
                    ),

                    title = { Text("Scaffold") },
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            tint = Color.White,
                            contentDescription = "Menu",
                            modifier = Modifier.clickable { scope.launch {
                                drawerState.apply {
                                    if (isClosed) open()
                                }}}

                        )
                    })
            },

        ) { innerPadding ->
            Surface( modifier = Modifier.padding(innerPadding)){
         NavHost(
             navController = navController as NavHostController,
             startDestination = "messages"
         ){
             composable("messages") { conv(msglist) }
             composable("affirmations") { AffirmationsApp() }
             composable("woof") { WoofApp() }
             // Add more composables for other destinations
         }


            }
        }
    }
}
    @Composable
    fun conv(msglist: List<Massage>, navController: NavController) {
        // ... Your conversation content

        // Button or action to navigate to affirmations
        Button(onClick = {
            navController.navigate("affirmations") // Navigate to affirmations screen
        }) {
            Text("Go to Affirmations")
        }
    }


    @Composable
    fun AffirmationsApp(navController: NavController) {
        // ... Affirmations content

        // Button to navigate back to messages (assuming it's the previous screen)
        Button(onClick = {
            navController.popBackStack() // Navigate back to the previous screen
        }) {
            Text("Back to Messages")
        }
    }

    @Composable
    fun WoofApp(navController: NavController) {
        // ... Woof content

        // Button or action to navigate to another screen
        Button(onClick = {
            // Navigate to another destination using navController.navigate("destination")
        }) {
            Text("Navigate to ...")
        }
    }

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ScaffoldTP3Theme {
        val mockNavController = rememberNavController()
        LayoutDesign(mockNavController)     }
}}
