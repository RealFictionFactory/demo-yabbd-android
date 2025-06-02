package com.rff.boingballdemo

import kotlinx.serialization.Serializable

sealed interface MainGraph {
    @Serializable
    data object Home : MainGraph

    @Serializable
    data object Preferences : MainGraph
}
