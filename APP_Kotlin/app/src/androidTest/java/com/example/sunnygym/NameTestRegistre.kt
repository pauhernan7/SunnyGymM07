package com.example.sunnygym

class NameTestRegistre {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val viewModel = ActivityRegistreViewModel()
}