package com.example.messengerappdemo.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import  com.example.messengerappdemo.R
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController


@Composable
fun Search(
    searchViewModel: SearchViewModel = viewModel(),
){
    Surface(
        color = MaterialTheme.colors.background
    ) {
        Scaffold(
            topBar = { SearchAppBar(searchViewModel) }
        ) {
            Box {
                UsersList(searchViewModel)
            }
        }
    }
}

@Composable
private fun UsersList(
    viewModel: SearchViewModel,
) {
    val usersList = viewModel.list.observeAsState().value

    LazyColumn(
        modifier = Modifier.padding(bottom = 48.dp)
    ) {
        if (!usersList.isNullOrEmpty()) {
            items(usersList) { user ->
                Text(
                    text = user.userName,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 12.dp)
                        .wrapContentWidth(Alignment.Start)
                )
            }
        }
    }
}

@Composable
private fun SearchAppBar(
    viewModel: SearchViewModel,
) {
    // Immediately update and keep track of query from text field changes.
    var query: String by rememberSaveable { mutableStateOf("") }
    var showClearIcon by rememberSaveable { mutableStateOf(false) }

    if (query.isEmpty()) {
        showClearIcon = false
    } else if (query.isNotEmpty()) {
        showClearIcon = true
    }

    TextField(
        value = query,
        onValueChange = { onQueryChanged ->
            // If user makes changes to text, immediately updated it.
            query = onQueryChanged
            // To avoid crash, only query when string isn't empty.
            if (onQueryChanged.isNotEmpty()) {
                // Pass latest query to refresh search results.
                viewModel.performQuery(onQueryChanged)
            }
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                tint = MaterialTheme.colors.onBackground,
                contentDescription = "Search Icon"
            )
        },
        trailingIcon = {
            if (showClearIcon) {
                IconButton(onClick = { query = "" }) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        tint = MaterialTheme.colors.onBackground,
                        contentDescription = "Clear Icon"
                    )
                }
            }
        },
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        //placeholder = { Text(text = stringResource(id = R.string.hint_search_bar)) },

        textStyle = MaterialTheme.typography.subtitle1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            autoCorrect = true,
            imeAction = ImeAction.Done
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background, shape = RectangleShape)
    )
}


@Preview
@Composable
fun SearchPreview(){
    Search()
}