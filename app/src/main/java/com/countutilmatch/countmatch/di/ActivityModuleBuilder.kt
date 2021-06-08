package com.countutilmatch.countmatch.di
import com.countutilmatch.countmatch.ui.adding.AddingActivity
import com.countutilmatch.countmatch.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModuleBuilder {
    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector()
    abstract fun contributeAddingActivity(): AddingActivity

}
