package com.example.searchimageapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.searchimageapp.network.model.Hit

@Preview(showBackground = true)
@Composable
fun HomeContent(viewModel: MainViewModel = hiltViewModel()) {

    val result = viewModel.list.value
    val query: MutableState<String> = remember {
        mutableStateOf("")
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(8.dp)) {

            // For Search
            OutlinedTextField(value = query.value, onValueChange = {
                query.value = it
                viewModel.getFlowersList(query.value)
            }, enabled = true, singleLine = true, leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }, label = { Text(text = "Search here...") }, modifier = Modifier.fillMaxWidth())

            // Handle case if loading
            if (result.isLoading) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            // Handle case for Error
            if (result.error.isNotEmpty() || result.error.isNotBlank()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(text = result.error, modifier = Modifier.align(Alignment.Center))
                }
            }

            // Case when data is not empty
            // For Grid
            if (result.data?.isNotEmpty() == true) {
                LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                    viewModel.list.value.data?.let { it ->
                        items(it)
                        {
                            MyGridItem(it)
                        }

                    }
                }
            }

        }
    }
}

@Composable
fun MyGridItem(hit: Hit) {

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(200.dp)
    ) {

        Column {
            Image(
                painter = rememberAsyncImagePainter(model = hit.largeImageURL),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
            )

            Text(
                text = hit.tags,
                style = TextStyle(fontSize = 16.sp, fontFamily = FontFamily.Default),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxHeight()
                    .align(Alignment.CenterHorizontally),
                maxLines = 2
            )
        }

    }
}
