package com.example.inhilenttest.compose

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.inhilenttest.util.Constant
import com.example.inhilenttest.compose.viewmodel.ProductListViewModel
import com.example.inhilenttest.screen.Screens

@Composable
fun BottomBarNavigate(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: ProductListViewModel
) {
    val items = listOf(Constant.PRODUCTS, Constant.FAVORITES)
    val icons = listOf(Icons.Filled.Home, Icons.Filled.Favorite)
    var selectedItem by remember { mutableIntStateOf(0) }


    NavigationBar(modifier = modifier.height(height = 50.dp)) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(icons[index], contentDescription = item) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    viewModel.setTitle(items[index])
                    navController.popBackStack()
                    navController.navigate(Screens.ProductList.route)
                }
            )
        }
    }
}