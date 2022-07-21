package com.example.exchangeratestracking.di.component

import androidx.lifecycle.ViewModel
import com.example.exchangeratestracking.data.local.db.MainDataBase
import com.example.exchangeratestracking.data.remote.api.ExchangeRatesApiService
import com.example.exchangeratestracking.di.ViewModelFactory
import com.example.exchangeratestracking.di.ViewModelKey
import com.example.exchangeratestracking.di.scope.ScreenScope
import com.example.exchangeratestracking.presentation.favourite.FavouriteViewModel
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap

@Component(modules = [FavouriteScreenModule::class])
@ScreenScope
interface FavouriteScreenComponent {

    fun viewModelFactory(): ViewModelFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun service(exchangeRatesApiService: ExchangeRatesApiService): Builder
        @BindsInstance
        fun db(mainDataBase: MainDataBase): Builder

        fun build(): FavouriteScreenComponent
    }
}

@Module
abstract class FavouriteScreenModule {

    @Binds
    @IntoMap
    @ViewModelKey(FavouriteViewModel::class)
    abstract fun favouriteScreenViewModel(viewModel: FavouriteViewModel): ViewModel
}