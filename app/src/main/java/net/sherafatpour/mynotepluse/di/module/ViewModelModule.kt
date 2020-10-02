package net.sherafatpour.mynotepluse.di.module

import androidx.lifecycle.ViewModel
import net.sherafatpour.mynotepluse.NoteViewModel
import net.sherafatpour.mynotepluse.di.key.ViewModelKey
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