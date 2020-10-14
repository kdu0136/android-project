package kim.dongun.paletteapp.di

import kim.dongun.paletteapp.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {
    viewModel(qualifier = named("mainViewModel")) { MainViewModel() }
}
