package com.example.exchangeratestracking.di.component

import androidx.lifecycle.ViewModel
import com.example.exchangeratestracking.data.remote.api.ExchangeRatesApiService
import com.example.exchangeratestracking.di.ViewModelFactory
import com.example.exchangeratestracking.di.ViewModelKey
import com.example.exchangeratestracking.di.scope.ScreenScope
import com.example.exchangeratestracking.presentation.home.HomeViewModel
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap

@Component(modules = [HomeScreenModule::class])
@ScreenScope
interface HomeScreenComponent {

    fun viewModelFactory(): ViewModelFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun service(exchangeRatesApiService: ExchangeRatesApiService): Builder

        fun build(): HomeScreenComponent
    }

}

@Module
abstract class HomeScreenModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun homeScreenViewModel(viewModel: HomeViewModel): ViewModel
}