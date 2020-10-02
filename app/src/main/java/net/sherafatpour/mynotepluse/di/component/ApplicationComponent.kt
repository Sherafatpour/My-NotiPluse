package net.sherafatpour.mynotepluse.di.component

import android.content.Context
import net.sherafatpour.mynotepluse.NoteApplication
import net.sherafatpour.mynotepluse.di.module.ActivityModule
import net.sherafatpour.mynotepluse.di.module.AppModule
import net.sherafatpour.mynotepluse.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class , ViewModelModule::class , AppModule::class , ActivityModule::class])
interface ApplicationComponent : AndroidInjector<NoteApplication>  {

    @Component.Factory
    interface Factory{

        fun create(@BindsInstance context: Context): ApplicationComponent

    }

}