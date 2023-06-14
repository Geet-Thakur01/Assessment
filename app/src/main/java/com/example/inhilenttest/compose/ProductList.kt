package com.example.inhilenttest.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.inhilenttest.compose.viewmodel.ProductListViewModel
import com.example.inhilenttest.screen.DATA
import com.example.inhilenttest.screen.Screens
import com.example.inhilenttest.ui.theme.InhilentTestTheme
import com.google.gson.Gson

@Composable
fun ProductListGrid(
    modifier: Modifier = Modifier,
    viewModel: ProductListViewModel,
    contentPadding: PaddingValues,
    navController: NavController,
) {

    val productList by viewModel.productLiveData.observeAsState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),

        ) {
        val listdata = if(viewModel.titleChange.value=="Products")
            productList?.products
        else
            productList?.products?.filter { it.isInWishlist }

        listdata?.let {
            items(it) { item ->
                ProductElement(
                    imgUrl = item.imageURL,
                    title = item.title,
                    brand = item.brand,
                    prize = "\$${item.price[0].value}",
                    Modifier.clickable {
                        viewModel.addProduct(item)
                        navController.navigate(Screens.Details.route)
                    }
                )
            }
        }
    }
}

