package com.example.exchangeratestracking.di.module

import com.example.exchangeratestracking.presentation.home.HomeViewModel
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun homeViewModel(): HomeViewModel
}