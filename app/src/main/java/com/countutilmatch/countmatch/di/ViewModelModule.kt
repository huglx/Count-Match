package com.countutilmatch.countmatch.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.countutilmatch.countmatch.di.ViewModelKey
import com.countutilmatch.countmatch.ui.adding.AddingViewModel
import com.countutilmatch.countmatch.ui.edit.EditViewModel
import com.countutilmatch.countmatch.ui.main.MainViewModel
import com.countutilmatch.countmatch.ui.settings.SettingsViewModel
import com.countutilmatch.countmatch.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel:MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddingViewModel::class)
    abstract fun bindAddingViewModel(viewModel:AddingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindSettingsViewModel(viewModel:SettingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditViewModel::class)
    abstract fun bindEditViewModel(viewModel:EditViewModel): ViewModel
}
