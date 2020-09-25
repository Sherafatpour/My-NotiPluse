package com.example.mynotipluse.di.module

import com.example.mynotipluse.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun provideMainActivity(): MainActivity

}