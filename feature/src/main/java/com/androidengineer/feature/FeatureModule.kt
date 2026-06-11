package com.androidengineer.feature

import com.androidengineer.feature.viewmodels.PostsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val postViewModelModule = module {
    viewModelOf(::PostsViewModel)
}