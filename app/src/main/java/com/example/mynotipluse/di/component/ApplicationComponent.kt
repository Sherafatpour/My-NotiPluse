package com.example.mynotipluse.di.component

import android.content.Context
import com.example.mynotipluse.NoteApplication
import com.example.mynotipluse.di.module.ActivityModule
import com.example.mynotipluse.di.module.AppModule
import com.example.mynotipluse.di.module.ViewModelModule
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