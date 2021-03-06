package com.example.atry

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.atry.ui.theme.dark_purple
import com.example.atry.ui.theme.light_gray
import com.example.atry.ui.theme.light_green
import com.example.atry.ui.theme.light_purple
import com.example.atry.viewModels.DeleteResourceViewModel
import com.example.atry.viewModels.ResourcesViewModel
import com.plcoding.ktorclientandroid.data.remote.PostsService
import com.plcoding.ktorclientandroid.data.remote.dto.PostResponse


@ExperimentalMaterialApi
@Composable
fun  resourcesScreen(navController: NavController, featureChoice:String?){
    val viewModel: ResourcesViewModel = hiltViewModel()
    val deleteResourceViewMode:DeleteResourceViewModel = hiltViewModel()
    val deleteDialog = remember {
        mutableStateOf(false)
    }
    val deletingResource = remember {
        mutableStateOf("-1")
    }
    val deletingResourceName = remember {
        mutableStateOf(" ")
    }
    val service = PostsService.create()
    val posts = produceState<List<PostResponse>>(
        initialValue = emptyList(),
        producer = {
            value = service.getPosts()
        }
    )
    CircularProgressBar(isDisplayed = viewModel.state.value.isLoading)
    LazyColumn(modifier = Modifier.padding(bottom = 50.dp)){

        itemsIndexed(viewModel.state.value.resources){index, item ->
            resourceCard(navController = navController,item.unique_id,item.name,item.description, Modifier.fillMaxSize()) {
                Icon(
                    Icons.Filled.Delete,
                    "",
                    tint = Color(0xFF4552B8),
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 10.dp)
                        .clickable {
                            deleteDialog.value = true
                            deletingResource.value = item.unique_id.toString()
                            deletingResourceName.value = item.name
                        }
                )
            }
        }
    }
    Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.End , modifier = Modifier.fillMaxSize()){
        Icon(
            Icons.Filled.AddCircle,"",tint = Color(0xFF4552B8),
            modifier = Modifier
                .size(130.dp)
                .padding(bottom = 50.dp)
                .clickable { navController.navigate(Screen.resourceFormScreen.route) })
    }
    if (deleteDialog.value) {
        AlertDialog(
            onDismissRequest = { deleteDialog.value = false },
            title = { Text(text = "Delete Resource", color = Color.Black) },
            text = {
                Text(
                    text = "Do You want to delete ${deletingResourceName.value}",
                    color = dark_purple
                )
            },

            confirmButton = {

                TextButton(
                    onClick = {
                        deleteDialog.value = false
                        deleteResourceViewMode.deleteResource(deletingResource.value.toInt())
                        navController.navigate(Screen.resourcesScreen.withArgs("resources"))




                    }) {
                    Text(text = "Confirm", color = light_green)
                }

            },
            dismissButton = {
                TextButton(
                    onClick = {
                        deleteDialog.value = false
                    }) {
                    Text(text = "Cancel", color = light_purple)
                }
            },

        )
    }


}