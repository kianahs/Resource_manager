package com.example.atry

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController

@ExperimentalMaterialApi
@Composable
fun Navigation(){

    val arrayList = ArrayList<Resource>()
    arrayList.add(Resource("CNC1","lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum"))
    arrayList.add(Resource("CNC2","lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum"))
    arrayList.add(Resource("CNC3","lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum"))
    arrayList.add(Resource("CNC4","lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem "))


    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.loginScreen.route ){
        composable(route = Screen.loginScreen.route){
            loginScreen(navController = navController)
        }
        composable(
            route = Screen.featuresScreen.route + "/{name}",
            arguments = listOf(
                navArgument("name"){
                    type = NavType.StringType
                    defaultValue = "Guest"
                    nullable = true

                }
            )
        ){entry ->
            featuresScreen(name= entry.arguments?.getString("name"), navController = navController)

        }
        composable(
            route = Screen.resourcesScreen.route + "/{title}",
            arguments = listOf(
                navArgument("title"){
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true

                }
            )
        ){entry ->
            resourcesScreen(arrayList,featureChoice= entry.arguments?.getString("title") )

        }

    }

}



@ExperimentalMaterialApi
@Composable
fun resourcesScreen(resources: List<Resource>, featureChoice:String?){
    LazyColumn(){
        itemsIndexed(resources){index, item ->
            resourceCard(item.name,item.description, Modifier.fillMaxSize(),{Icon(Icons.Filled.Settings,"",tint = Color(0xFF4552B8),modifier = Modifier.size(40.dp))})
        }
    }
}


@ExperimentalMaterialApi
@Composable
fun resourceCard( resourceName:String,description:String, modifier: Modifier = Modifier, icon_shape: @Composable() () -> Unit){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { },
        elevation = 10.dp,
        shape = RoundedCornerShape(15.dp),
        backgroundColor = Color(0xFFE3DFDC)

    ) {
        Image(painter = painterResource(id = R.drawable.resource), contentDescription = null)
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    buildAnnotatedString {
//                    append("welcome to ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color(0xFF4552B8), fontSize = 25.sp)
                        ) {
                            append(resourceName)
                        }
                    }
                )




            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(buildAnnotatedString {
//                    append("welcome to ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color(0xFF4552B8))
                ) {
                    append("Discription:\n")
                }
                withStyle(style = SpanStyle(color = Color.Gray)
                ) {
                    append(description)
                }
            })
        }


    }


}


@Composable
fun loginScreen(navController: NavController){

    var usernameState by rememberSaveable { mutableStateOf("") }
//    val passwordState = remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            buildAnnotatedString {
//                    append("welcome to ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color(0xFF4552B8), fontSize = 50.sp)
                ) {
                    append("Welcome !")
                }
            }
        )
        Spacer(modifier = Modifier.padding(15.dp))
        TextField(
            value = usernameState,
            onValueChange = { usernameState = it },
            label = { Text("Enter Username") }
        )
//        textInput(textFieldName = "Username",true)
        Spacer(modifier = Modifier.padding(5.dp))
        textInput(textFieldName = "Password", false)
        Spacer(modifier = Modifier.padding(15.dp))
        Button(onClick = { navController.navigate(Screen.featuresScreen.withArgs(usernameState))}) {
            Text(text = "Login")

        }


    }



}
@Composable
fun textInput(textFieldName: String, show: Boolean ) {

    var textState by rememberSaveable { mutableStateOf("") }
    if (show) {
        TextField(
            value = textState,
            onValueChange = { textState = it },
            label = { Text("Enter $textFieldName") }
        )
    }else{
        TextField(
            value = textState,
            onValueChange = { textState = it },
            label = { Text("Enter $textFieldName") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun featuresScreen (name:String?, navController: NavController){

    LazyColumn() {
        itemsIndexed(
            listOf<cardStructure>(
                cardStructure("Resources", { Icon(Icons.Filled.Add,"",tint = Color(0xFF4552B8),modifier = Modifier.size(40.dp)) }),
                cardStructure("Tasks", { Icon(Icons.Filled.Add,"",tint = Color(0xFF4552B8),modifier = Modifier.size(40.dp)) }),
                cardStructure("Setting", { Icon(Icons.Filled.Settings,"",tint = Color(0xFF4552B8),modifier = Modifier.size(40.dp)) }),
                cardStructure("Account", { Icon(Icons.Filled.Person,"",tint = Color(0xFF4552B8),modifier = Modifier.size(40.dp)) }),
                cardStructure("FAQ", { Icon(Icons.Filled.Search,"",tint = Color(0xFF4552B8),modifier = Modifier.size(40.dp)) }))

        ) { index, item ->
            cardElement(item.get_title(), Modifier.fillMaxSize(),item.get_icon(),navController = navController)
        }

    }

}
@ExperimentalMaterialApi
@Composable
fun cardElement( title:String, modifier: Modifier = Modifier, icon_shape: @Composable() () -> Unit, navController: NavController){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { },
        elevation = 10.dp,
        shape = RoundedCornerShape(15.dp),
        onClick = {
           navController.navigate(Screen.resourcesScreen.withArgs(title))
        }

    ) {
        Row(
            modifier = Modifier.padding(50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                buildAnnotatedString {
//                    append("welcome to ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color(0xFF4552B8), fontSize = 25.sp)
                    ) {
                        append(title)
                    }
                }
            )
            icon_shape()


        }
    }


}