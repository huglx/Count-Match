package com.countutilmatch.countmatch.di
import com.countutilmatch.countmatch.ui.adding.AddingActivity
import com.countutilmatch.countmatch.ui.edit.EditActivity
import com.countutilmatch.countmatch.ui.main.MainActivity
import com.countutilmatch.countmatch.ui.settings.SettingsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModuleBuilder {
    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector()
    abstract fun contributeAddingActivity(): AddingActivity

    @ContributesAndroidInjector()
    abstract fun contributeEditActivity(): EditActivity

    @ContributesAndroidInjector()
    abstract fun contributeSettingsActivity(): SettingsActivity
}
