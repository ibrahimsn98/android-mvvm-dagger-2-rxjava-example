package me.ibrahimsn.viewmodel.ui.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.ibrahimsn.viewmodel.di.module.NetworkModule;
import me.ibrahimsn.viewmodel.ui.detail.DetailsFragment;
import me.ibrahimsn.viewmodel.ui.list.ListFragment;

@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector(modules = {NetworkModule.class})
    abstract ListFragment provideListFragment();

    @ContributesAndroidInjector(modules = {NetworkModule.class})
    abstract DetailsFragment provideDetailsFragment();
}
