package com.example.compcalculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }

//            Column(Modifier.verticalScroll(rememberScrollState())) {
//
//                repeat(5)
//                {
//                    Row(modifier = Modifier.fillMaxWidth()) {
//
//
//                        repeat(2)
//                        {
//                            Grid(it)
//                        }
//                    }
//
//                }
//                }
//            }


            
         //   list()
        }

    @Composable
    fun Grid(num:Int){
      Card(elevation = 4.dp, modifier = Modifier.padding(8.dp)) {
          Box(modifier=Modifier.requiredSize(180.dp,180.dp))
          {
              Button(modifier = Modifier.fillMaxSize(), onClick = { /*TODO*/ }) {
                  Text(num.toString())
              }
          }
      }  
    }

    @Composable
    fun list(){

        val categories = listOf<String>(
            "Breakfast",
            "Lunch",
            "Dinner"
        )

        Column(modifier=Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween    ) {

            categories.forEach{
                MenuCategory(category = it)
            }
        }
    }

    @Composable
    fun MenuCategory(category:String){
        Button(onClick = { }, colors =ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
        modifier=Modifier.padding(5.dp),
        shape = RoundedCornerShape(4.dp)
        )
         {

            Text(text=category)
            }
    }

    @Composable
    fun bottomBar(){
        val navController = rememberNavController()

        Scaffold(drawerContent = {MyNavigation()},bottomBar = {MyBottomNavigation(navController = navController)}) {
            Box(Modifier.padding(it)){
                NavHost(navController = navController, startDestination = homeBottom.route){
                    composable(homeBottom.route)
                    {
                        HomeForBottomNavigation()
                    }
                    composable(settingBottom.route)
                    {
                        SettingForBottomNavigation()
                    }
                }
            }
        }
    }
  @Composable
  fun MyBottomNavigation(navController: NavController){

      val destinationsList = listOf<DestinationsBottom>(
          homeBottom,
          settingBottom
      )

      val selectedIndex = rememberSaveable {
          mutableStateOf(0)
      }

      BottomNavigation() {
          destinationsList.forEachIndexed{index, destination->
              BottomNavigationItem(label = {Text(text = destination.title)}, icon ={ Icon(
                  imageVector = destination.icon,
                  contentDescription =destination.title
              )}, selected = index==selectedIndex.value, onClick = {
                  selectedIndex.value=index
                  navController.navigate(destinationsList[index].route){
                      popUpTo(homeBottom.route)
                      launchSingleTop=true

                  }
              })
          }
      }
  }

    @Composable
    fun MyNavigation()
    {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = LogIn.route)
        {
            composable(LogIn.route)
            {
                MainPanel(navController)
            }
            composable(Dashboard.route)
            {
                HomePage()
            }

        }

    }
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun MainPanel(navController: NavHostController)
    {
        var email by rememberSaveable {
            mutableStateOf("")
        }
        var password by rememberSaveable {
            mutableStateOf("")
        }
        val context = LocalContext.current

        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()

        Scaffold(
            scaffoldState=scaffoldState,
            drawerContent = { DrawerPanel(scaffoldState=scaffoldState,scope=scope)},
            topBar = {
                TopAppBar(scaffoldState=scaffoldState,scope=scope)
            }
        ) {
            Column {
                Image(painter = painterResource(id = R.drawable.baseline_login_24), contentDescription = "Just dummy image",modifier= Modifier
                    .width(150.dp)
                    .height(150.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(end = 16.dp))


                Spacer(modifier = Modifier.height(60.dp))
                TextField(value = email, onValueChange = {email=it}, label = {Text("Email")}, modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color.Cyan

                    ))

                Spacer(modifier = Modifier.height(20.dp))
                TextField(value = password, onValueChange = {password=it}, label = {Text("Password")}, modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color.Cyan
                    ))

                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = {
                   navController.navigate(Dashboard.route)
                },modifier= Modifier
                    .padding(bottom = 30.dp)
                    .fillMaxWidth() ) {
                    Text(text = "Sign In")


                }
            }
        }



    }

    @Composable
    private fun TopAppBar(scaffoldState: ScaffoldState?=null,scope:CoroutineScope?=null) {
        Row(horizontalArrangement = Arrangement.SpaceBetween,modifier= Modifier
            .background(Color.Cyan)
            .fillMaxWidth()){
            IconButton(onClick = {
                scope?.launch { scaffoldState?.drawerState?.open() }
            }) {
                Image(painter = painterResource(id = R.drawable.baseline_menu_24), contentDescription = "Logo")
            }

        }
    }

}
