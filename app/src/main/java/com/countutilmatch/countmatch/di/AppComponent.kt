package com.countutilmatch.myapplicationdi

import com.countutilmatch.countmatch.Application
import com.countutilmatch.countmatch.di.ActivityModuleBuilder
import com.countutilmatch.countmatch.di.AppModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            ActivityModuleBuilder::class,
            AndroidInjectionModule::class,
            AppModule::class,
        ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        fun build(): AppComponent
    }

    fun inject(app: Application)
}
