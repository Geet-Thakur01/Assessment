package com.example.inhilenttest.compose

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.inhilenttest.R
import com.example.inhilenttest.compose.viewmodel.ProductListViewModel

@Composable
fun DetailsScreen(
    navController: NavController,
    padding: PaddingValues,
    viewModel: ProductListViewModel
) {

    val product = viewModel.product
    var asFev by remember { mutableIntStateOf(0) }
    asFev = if (product?.isInWishlist as Boolean) 1 else 0
    // ... your screen content
    Column(
        modifier = Modifier
            .padding(paddingValues = padding)
            .fillMaxWidth()
            .fillMaxHeight(),
    ) {
        AsyncImage(
            model = product.imageURL,
            contentDescription = "image loaded",
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.CenterHorizontally)
                .weight(0.5f),
            error = painterResource(id = R.drawable.picture)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "${product.title}}")
            Icon(imageVector = if (asFev == 0) Icons.Outlined.FavoriteBorder else Icons.Filled.Favorite,
                contentDescription = "my favorite",
                modifier = Modifier.clickable {
                   asFev= if(asFev==1) 0 else 1
                    product.isInWishlist = asFev == 1
                    Log.e("TAG121","${product.isInWishlist}")
                    viewModel.addRemoveFevProduct(product)
                })
        }
        Text(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth(), text = "${product.brand}"
        )
    }

}
