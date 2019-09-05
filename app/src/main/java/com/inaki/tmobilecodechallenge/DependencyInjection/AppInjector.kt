package com.inaki.tmobilecodechallenge.DependencyInjection

import com.inaki.tmobilecodechallenge.Network.ApiService
import com.inaki.tmobilecodechallenge.UI.Fragments.SearchUserFragment
import com.inaki.tmobilecodechallenge.ViewModel.ReposViewModel
import org.koin.android.experimental.dsl.viewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.create

val networkModule = module {
    single { retrofitClient().create<ApiService>() }
}

val viewModelModule = module {
    viewModel { ReposViewModel(get()) }
}

val fragmentModule = module {
    single { SearchUserFragment() }
}