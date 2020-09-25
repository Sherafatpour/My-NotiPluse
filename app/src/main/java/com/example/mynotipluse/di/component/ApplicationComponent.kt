package com.example.mynotipluse.di.module

import android.content.Context
import com.example.mynotipluse.NoteApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class , AppModule::class , ActivityModule::class])
interface ApplicationComponent : AndroidInjector<NoteApplication>  {

    @Component.Factory
    interface Factory{

        fun create(@BindsInstance context: Context):ApplicationComponent

    }

}