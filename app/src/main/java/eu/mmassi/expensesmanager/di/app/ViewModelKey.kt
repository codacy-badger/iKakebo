package eu.mmassi.expensesmanager.di.app

import dagger.MapKey
import androidx.lifecycle.ViewModel
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)