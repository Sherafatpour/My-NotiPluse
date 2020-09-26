package com.example.mynotipluse.di.module

import androidx.lifecycle.ViewModel
import com.example.mynotipluse.NoteViewModel
import com.example.mynotipluse.di.key.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NoteViewModel::class)
    abstract fun provideMainActivity(vm: NoteViewModel): ViewModel

}