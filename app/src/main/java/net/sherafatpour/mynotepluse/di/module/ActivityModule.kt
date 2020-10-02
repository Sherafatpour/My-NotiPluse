package net.sherafatpour.mynotepluse.di.module

import net.sherafatpour.mynotepluse.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun provideMainActivity(): MainActivity

}