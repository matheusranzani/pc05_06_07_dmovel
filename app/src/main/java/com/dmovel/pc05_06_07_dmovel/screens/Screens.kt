package com.dmovel.pc05_06_07_dmovel.screens

sealed class Screens(val screen: String) {
    data object Home: Screens("home")
    data object History: Screens("history")
    data object MoreData: Screens("moreData")
}