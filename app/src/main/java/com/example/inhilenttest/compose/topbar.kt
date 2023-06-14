package com.example.inhilenttest.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.inhilenttest.util.Constant
import com.example.inhilenttest.R
import com.example.inhilenttest.compose.viewmodel.ProductListViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarLayout(modifier: Modifier, viewModel: ProductListViewModel) {
    val title: String by viewModel.titleChange.observeAsState(Constant.PRODUCTS)

    TopAppBar(
        title = {
            Text(
                title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = stringResource(id = R.string.shoping)
                )
            }
        }
    )
}