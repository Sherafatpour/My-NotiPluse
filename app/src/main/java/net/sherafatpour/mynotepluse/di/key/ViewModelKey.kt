package net.sherafatpour.mynotepluse.di.key

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass


@MapKey
@MustBeDocumented
annotation class ViewModelKey(val value:KClass<out ViewModel>)