package com.example.pokedex.utils

fun String.toUpperFirstChar(): String {
    return replaceFirstChar { it.uppercase() }
}