package me.ibrahimsn.viewmodel.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.ibrahimsn.viewmodel.ui.main.MainActivity;
import me.ibrahimsn.viewmodel.ui.main.MainFragmentBindingModule;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {MainFragmentBindingModule.class})
    abstract MainActivity bindMainActivity();
}
