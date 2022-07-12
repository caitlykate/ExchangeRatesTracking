package com.example.exchangeratestracking.di.component

import androidx.lifecycle.ViewModel
//import com.example.exchangeratestracking.di.ViewModelFactory
//import com.example.exchangeratestracking.di.scope.ScreenScope
//import com.example.exchangeratestracking.di.scope.ViewModelKey
import com.example.exchangeratestracking.presentation.home.HomeViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap

@Component(modules = [HomeScreenModule::class])
//@ScreenScope
interface HomeScreenComponent {
//
//    fun viewModelFactory(): ViewModelFactory
}

@Module
abstract class HomeScreenModule {

//    @Binds
//    @IntoMap
//    @ViewModelKey(HomeViewModel::class)
//    abstract fun homeScreenViewModel(viewModel: HomeViewModel): ViewModel
}