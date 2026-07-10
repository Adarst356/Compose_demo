package com.example.new_compose.modules.dashboard.emi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.new_compose.core.composables.ErrorTextView
import com.example.new_compose.core.composables.Loader
import com.example.new_compose.core.network.UiState

@Composable
fun EmiScreen(
    mainNavController: NavHostController = rememberNavController(),
    viewModel: EmiViewModel = hiltViewModel()
) {
    val emiState by viewModel.emiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SearchSection(
            query = searchQuery,
            onQueryChange = { viewModel.onSearchQueryChange(it) }
        )

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            when (val state = emiState) {
                UiState.None -> {}

                UiState.Loading -> {
                    Loader(modifier = Modifier.align(Alignment.Center))
                }

                is UiState.Error -> {
                    ErrorTextView(
                        error = state.message,
                        modifier = Modifier.align(Alignment.Center),
                        onRetry = { viewModel.getEmiCustomers() }
                    )
                }

                is UiState.Success -> {
                    val customers = state.data
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(bottom = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            items(
                                customers, key = { it.id }) {
                                customer ->
                                CustomerItem(
                                    "customer",
                                    title = customer.title,
                                    albumId =customer.albumId,
                                    photoId = customer.id,
                                    onClick = (
                                            {
                                               /* mainNavController.navigate(
                                                    "customer_details/${customer.id}"*/
                                            })
                                )



                            }
                        }

                }
            }
        }
    }
}

@Composable
fun SearchSection(
    query: String,
    onQueryChange: (String) -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        leadingIcon = {
            Icon(Icons.Default.Search, null, tint = Color.Gray)
        },
        placeholder = {
            Text("Search by title or ID...", color = Color.Gray)
        },
        shape = RoundedCornerShape(14.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color.White.copy(alpha = 0.05f),
            focusedContainerColor = Color.White.copy(alpha = 0.05f),
            unfocusedBorderColor = Color.Gray.copy(alpha = 0.3f),
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White
        ),
        singleLine = true
    )
}



@Composable
fun CustomerItem(
    image: String,
    title: String,
    albumId: Int,
    photoId: Int,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable {
                onClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1C1C1E)
        ),
        shape = RoundedCornerShape(14.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = image,
                contentDescription = title,
                modifier = Modifier
                    .size(60.dp)
                    .clip(
                        RoundedCornerShape(12.dp)
                    ),
                contentScale = ContentScale.Crop
            )
            Spacer(
                modifier = Modifier.width(14.dp)
            )


            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 1,
                    color = Color.White
                )


                Spacer(
                    modifier = Modifier.height(4.dp)
                )


                Text(
                    text = "Album ID: $albumId",
                    color = Color.Gray,
                    fontSize = 13.sp
                )


                Text(
                    text = "Photo ID: $photoId",
                    color = Color(0xFF397AE3),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp
                )

            }


            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = Color.Gray
            )

        }
    }
}