package com.rff.boingballdemo.di

import com.rff.boingballdemo.data.local.AppSettings
import com.rff.boingballdemo.preferences.PreferencesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AppSettings(androidContext()) }

    viewModel { PreferencesViewModel(settings = get()) }
}
