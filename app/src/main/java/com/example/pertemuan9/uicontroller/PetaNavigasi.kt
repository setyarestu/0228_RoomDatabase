package com.example.pertemuan9.uicontroller

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pertemuan9.route.DestinasiDetailSiswa
import com.example.pertemuan9.route.DestinasiDetailSiswa.itemIdArg
import com.example.pertemuan9.route.DestinasiEditSiswa
import com.example.pertemuan9.route.DestinasiEntry
import com.example.pertemuan9.route.DestinasiHome
import com.example.pertemuan9.view.DetailSiswaScreen
import com.example.pertemuan9.view.EditSiswaScreen
import com.example.pertemuan9.view.EntrySiswaScreen
import com.example.pertemuan9.view.HomeScreen

@Composable
fun SiswaApp(navController: NavHostController = rememberNavController()) {
    HostNavigasi(navController = navController)
}

@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                //edit 1 : tambahkan parameter navigateToItemupdate
                navigateToItemUpdate = { // DIUBAH: huruf U kapital
                    navController.navigate(route = "${DestinasiDetailSiswa.route}/$it") // DIUBAH: $(it) jadi $it
                }
            )
        }
        composable(DestinasiEntry.route) {
            EntrySiswaScreen(navigateBack = { navController.popBackStack() })
        }
        //edit 2 : tambahkan 2 composable route
        composable(
            route = DestinasiDetailSiswa.routeWithArgs,
            arguments = listOf(navArgument(name = itemIdArg) {
                type = NavType.IntType
            })
        ) {
            DetailSiswaScreen(
                navigateToEditItem = { // DIAKTIFKAN (sebelumnya dikomentari)
                    navController.navigate("${DestinasiEditSiswa.route}/$it")
                },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(
            route = DestinasiEditSiswa.routeWithArgs,
            arguments = listOf(navArgument(name = DestinasiEditSiswa.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            EditSiswaScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}