package com.example.inhilenttest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.inhilenttest.compose.DetailsScreen
import com.example.inhilenttest.compose.ProductListGrid
import com.example.inhilenttest.compose.TopBarLayout
import com.example.inhilenttest.compose.BottomBarNavigate
import com.example.inhilenttest.compose.ErrorMsg
import com.example.inhilenttest.compose.viewmodel.ProductListViewModel
import com.example.inhilenttest.screen.DATA
import com.example.inhilenttest.screen.Screens
import com.example.inhilenttest.ui.theme.InhilentTestTheme
import com.example.inhilenttest.util.Util
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val productListViewModel: ProductListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            InhilentTestTheme {

                    AppMainScreen(productListViewModel = productListViewModel)
                    productListViewModel.fetchProducts()

            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppMainScreen(
    navController: NavHostController = rememberNavController(),
    productListViewModel: ProductListViewModel,
) {
    Scaffold(
        topBar = {
            TopBarLayout(modifier = Modifier,
                viewModel = productListViewModel)
        },
        bottomBar = {
            BottomBarNavigate(
                modifier = Modifier,
                navController = navController,
                viewModel = productListViewModel
            )
        },
        content = { innerPadding ->
            NavHost(navController = navController,
                startDestination = Screens.ProductList.route) {
                composable(route = Screens.ProductList.route) {
                    if (Util.InternetIsConnected().not()) {
                        ErrorMsg(msg = R.string.internet_not_available)
                    } else {
                        if(productListViewModel.productLiveData.value==null){
                            productListViewModel.fetchProducts()
                        }
                        ProductListGrid(
                            contentPadding = innerPadding,
                            navController = navController,
                            viewModel = productListViewModel
                        )
                    }
                }
                composable(Screens.Details.route,
                    arguments = listOf(navArgument(DATA) {
                    type = NavType.StringType
                })) {
                    DetailsScreen(
                        padding = innerPadding,
                        viewModel = productListViewModel
                    )
                }
            }
        }
    )
}




