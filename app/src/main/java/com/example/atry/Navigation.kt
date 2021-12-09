package com.example.atry

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.CheckCircle

import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.plcoding.ktorclientandroid.data.remote.PostsService
import com.plcoding.ktorclientandroid.data.remote.dto.PostResponse
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
            resourcesScreen(navController = navController,featureChoice= entry.arguments?.getString("title") )

        }
        composable(
            route = Screen.accountFormScreen.route + "/{title}",
            arguments = listOf(
                navArgument("title"){
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true

                }
            )
        ){entry ->
            accountForm(featureChoice= entry.arguments?.getString("title") )

        }
        composable(
            route = Screen.resourceFormScreen.route

        ){
            resourceFrom(navController = navController)

        }
        composable(
            route = Screen.tasksScreen.route

        ){
            tasksScreen(navController = navController)

        }
        composable(
            route = Screen.taskFormScreen.route

        ){
            taskForm(navController = navController)

        }

    }

}

@Composable
fun taskForm(navController : NavController){

//    val resourceNameState = remember { mutableStateOf(TextFieldValue()) }
//    val resourceDescriptionState = remember { mutableStateOf(TextFieldValue()) }
    val shape = RoundedCornerShape(topStart = 80.dp)
    Column(modifier = Modifier.background(Color(0xFF4552B8))) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.1f)
            .background(Color(0xFF4552B8))
        ){
            Row(modifier= Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Icon(Icons.Filled.AccountCircle,"",tint = Color.White,modifier = Modifier.size(50.dp))
//                Icon(Icons.Filled.AccountCircle,"",tint = Color.White,modifier = Modifier.size(50.dp))

            }
        }

        Box(modifier = Modifier
            .clip(shape)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(){
                    Text(
                        buildAnnotatedString {
//                    append("welcome to ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color(0xFF4552B8), fontSize = 40.sp)
                            ) {
                                append("Add task")
                            }
                        }
                    )
                    Icon(Icons.Filled.CheckCircle,"",tint = Color(0xFF4552B8),modifier = Modifier.size(50.dp))


                }
                Spacer(modifier = Modifier.padding(15.dp))
                textInput(textFieldName = "Task name",true)
                Spacer(modifier = Modifier.padding(15.dp))
                textInput(textFieldName = "Duration", false)
                Spacer(modifier = Modifier.padding(15.dp))
                textInput(textFieldName = "priority", false)
                Spacer(modifier = Modifier.padding(15.dp))
                textInput(textFieldName = "Resource", false)
                Spacer(modifier = Modifier.padding(15.dp))
                Icon(Icons.Filled.AddCircle,"",tint = Color(0xFF4552B8),modifier = Modifier
                    .size(40.dp)
                    .clickable { navController.navigate(Screen.tasksScreen.route) }) //bayad eslah she be safeye resourcei ke azash umade


            }

        }



    }



}


@ExperimentalMaterialApi
@Composable
fun tasksScreen(navController: NavController){

    Box(modifier = Modifier.fillMaxWidth()) {

        Text(
            buildAnnotatedString {
//                    append("welcome to ")

                withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color(0xFF4552B8), fontSize = 40.sp)
                ) {
                    append("Today")
                }

            },
            modifier = Modifier.padding(10.dp)
        )

        dayCardScroller()
    }


    LazyColumn(modifier = Modifier.padding(top=200.dp, start = 50.dp,end=10.dp)){

        itemsIndexed(
           listOf(Task("refactor",50,500,Resource("CNC","Nothing")),
               Task("check",50,500,Resource("CNC","Nothing")),
               Task("repair",50,500,Resource("CNC","Nothing")),
               Task("run",50,500,Resource("CNC","Nothing")),
               Task("repair",50,500,Resource("CNC","Nothing")),
               Task("run",50,500,Resource("CNC","Nothing"))
           )
        ){index, item ->
            Box(){
                Row() {
                    Icon(Icons.Filled.Info,"",tint = Color(0xFF4552B8),
                        modifier = Modifier
                            .size(40.dp)
                            .padding(top = 20.dp)
                            )
                    taskCard(modifier = Modifier,task = item )
                }

            }

        }
    }
    Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.End , modifier = Modifier.fillMaxSize()){
        Icon(Icons.Filled.AddCircle,"",tint = Color(0xFF4552B8),
            modifier = Modifier
                .size(80.dp)
                .clickable { navController.navigate(Screen.taskFormScreen.route) })
    }


}

@ExperimentalMaterialApi
@Composable
fun taskCard(  modifier: Modifier = Modifier, task: Task){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clickable { },
        elevation = 10.dp,
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Color(0xFF673AB7)

    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(Modifier.padding(top=20.dp)) {
                Text(
                    buildAnnotatedString {
//                    append("welcome to ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color.White, fontSize = 20.sp)
                        ) {
                            append(task.get_task_name())
                        }
                    }
                )

                Text(buildAnnotatedString {
//                    append("welcome to ")

                    withStyle(style = SpanStyle(color = Color.White, fontSize = 10.sp),

                        ) {
                        append("Resource:"+task.get_task_resource().resource_name+"\n")
                        append("Priority:"+task.get_task_priority().toString()+"\n")
                        append("Duration:"+task.get_task_duration().toString()+"\n")
                    }
                })
            }
            Column() {
                Image(painter = painterResource(id = R.drawable.task), contentDescription = null ,Modifier.size(80.dp))
            }
        }

    }


}

@ExperimentalMaterialApi
@Composable
fun dayCard(  modifier: Modifier = Modifier, date: Date){

    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { },
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color(0xFFF3F3F1)

    ) {
        Column(Modifier.padding(10.dp)) {
            Text(
                buildAnnotatedString {

                    withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, color = Color.Gray, fontSize = 15.sp)
                    ) {
                        append(date.getDayName())
                    }
                }
            )

            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold,color = Color.Black, fontSize = 15.sp),


                        ) {
                        append(date.getDayNumber())
                    }
                },
                modifier = Modifier.padding(start= 8.dp, end= 8.dp))
        }



    }


}
@ExperimentalMaterialApi
@Composable
fun dayCardScroller() {
    val items = listOf<Date>(Date("Sat", "1"),Date("Sun", "2"),
        Date("Mon", "3"),
        Date("Tue", "4"),
        Date("Wed", "5"),
        Date("Thu", "6"),
        Date("Fri", "7"))

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp, bottom = 10.dp),

        ) {
        itemsIndexed(items) { index, item ->
            dayCard( modifier= Modifier,item)
        }

    }
}



@Composable
fun resourceFrom(navController: NavController) {

    val resourceNameState = remember { mutableStateOf(TextFieldValue()) }
    val resourceDescriptionState = remember { mutableStateOf(TextFieldValue()) }
    val shape = RoundedCornerShape(topStart = 80.dp)

    Column(modifier = Modifier.background(Color(0xFF4552B8))) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.35f)
            .background(Color(0xFF4552B8))
        )


        Box(modifier = Modifier
            .clip(shape)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    buildAnnotatedString {
//                    append("welcome to ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color(0xFF4552B8), fontSize = 40.sp)
                        ) {
                            append("Add resource!")
                        }
                    }
                )
                Spacer(modifier = Modifier.padding(15.dp))
                textInput(textFieldName = "Resource name",true)
                Spacer(modifier = Modifier.padding(5.dp))
                textInput(textFieldName = "Description", false)
                Spacer(modifier = Modifier.padding(15.dp))
                Icon(Icons.Filled.AddCircle,"",tint = Color(0xFF4552B8),
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { navController.navigate(Screen.resourcesScreen.withArgs("Resources")) })


            }

        }



    }

}

@Composable
fun accountForm(featureChoice: String?) {
    val resourceNameState = remember { mutableStateOf(TextFieldValue()) }
    val resourceDescriptionState = remember { mutableStateOf(TextFieldValue()) }
    val shape = RoundedCornerShape(topStart = 80.dp)
    val shape2 = CircleShape
    Column(modifier = Modifier.background(Color(0xFF4552B8)),horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.10f)
            .background(Color(0xFF4552B8))
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.ExtraBold, color = Color(0xFFABA0E7), fontSize = 40.sp
                            )
                        ) {
                            append("Edit Profile")
                        }
                    }
                )
            }
        }
        Box(modifier = Modifier
            .clip(shape)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier
                    .size(120.dp)
                    .fillMaxHeight(10f)
                    .clip(shape2)
                    .background(Color.Gray)
                ){

                    Spacer(modifier = Modifier.padding(5.dp))
                    Icon(Icons.Filled.Person,"",tint = Color(0xFFA3ACEE),modifier = Modifier.size(120.dp))

                }

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.20f)
                    .background(Color.White)

                ){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.4f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.padding(5.dp))
                        Icon(Icons.Filled.AddCircle,"photo",tint = Color(0xFFB9BDDA),modifier = Modifier.size(40.dp))


                    }
                }
                textInput(textFieldName = "Your name", true)
                Spacer(modifier = Modifier.padding(5.dp))
                textInput(textFieldName = "Username",true)
                Spacer(modifier = Modifier.padding(5.dp))
                textInput(textFieldName = "Password", false)
                Spacer(modifier = Modifier.padding(5.dp))
                textInput(textFieldName = "Company name", true)
                Spacer(modifier = Modifier.padding(15.dp))
                Icon(Icons.Outlined.CheckCircle,"",tint = Color(0xFF626CC2),modifier = Modifier.size(40.dp))


            }

        }



    }

}



@ExperimentalMaterialApi
@Composable
fun resourcesScreen( navController: NavController,featureChoice:String?){
    val service = PostsService.create()
    val posts = produceState<List<PostResponse>>(
        initialValue = emptyList(),
        producer = {
            value = service.getPosts()
        }
    )
    LazyColumn(){
        itemsIndexed(posts.value){index, item ->
            resourceCard(navController = navController,item.name,item.description, Modifier.fillMaxSize(),{Icon(Icons.Filled.Settings,"",tint = Color(0xFF4552B8),modifier = Modifier.size(40.dp))})
        }
    }
    Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.End , modifier = Modifier.fillMaxSize()){
        Icon(Icons.Filled.AddCircle,"",tint = Color(0xFF4552B8),
            modifier = Modifier
                .size(80.dp)
                .clickable { navController.navigate(Screen.resourceFormScreen.route) })
    }


}


@ExperimentalMaterialApi
@Composable
fun resourceCard( navController: NavController,resourceName:String,description:String, modifier: Modifier = Modifier, icon_shape: @Composable() () -> Unit){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { navController.navigate(Screen.tasksScreen.route) },
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
            when(title){

                "Account" -> navController.navigate(Screen.accountFormScreen.withArgs(title))
                "Resources" -> navController.navigate(Screen.resourcesScreen.withArgs(title))


            }

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
@ExperimentalMaterialApi
@Composable
fun calendar() {
    val service = PostsService.create()
    val items = listOf("Winter","Spring","Summer","Autumn").map { " $it" }
    val month1= listOf("January","February","March")
    val month2= listOf("  April","    May","   June")
    val month3= listOf("    July","  August","September")
    val month4= listOf("October","November","December")
    val col = listOf<Long>(0xFF5DA0D1,0xFF669E68,0xFFE0D29D,0xFFCC845E)
    LazyColumn() {
        itemsIndexed(items) { index, item ->

            if(index==0)
                seasons(item,month1,col[index])
            if(index==1)
                seasons(item,month2,col[index])
            if(index==2)
                seasons(item,month3,col[index])
            if(index==3)
                seasons(item,month4,col[index])
        }

    }
}
@ExperimentalMaterialApi
@Composable
fun seasons(name :String, d : List<String>, col : Long) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { },
        elevation = 10.dp,
        shape = RoundedCornerShape(15.dp),
        backgroundColor = Color(col)


    ) {
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
                        withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color(
                            0xFFFFFFFF
                        ), fontSize = 30.sp)
                        ){append("             "+name) }


                    }
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier) {
                BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                    // LazyRow to display your items horizontally
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        state = rememberLazyListState()
                    ) {
                        itemsIndexed(d) { index, item ->
                            Card(
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(123.dp) // here is the trick
                                    .padding(3.dp)
                            ) {
                                Text("  "+d.get(index),color = Color(col),fontFamily = FontFamily.Serif,fontSize = 18.sp,fontWeight = FontWeight.ExtraBold) // card's content


                            }
                        }

                    }
                }
            }
        }


    }
}
