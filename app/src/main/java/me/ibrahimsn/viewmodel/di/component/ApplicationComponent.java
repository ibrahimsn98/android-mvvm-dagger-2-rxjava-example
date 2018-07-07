package me.ibrahimsn.viewmodel.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;
import me.ibrahimsn.viewmodel.base.BaseApplication;
import me.ibrahimsn.viewmodel.di.module.ActivityBindingModule;
import me.ibrahimsn.viewmodel.ui.detail.DetailsFragment;
import me.ibrahimsn.viewmodel.ui.list.ListFragment;
import me.ibrahimsn.viewmodel.di.module.ViewModelModule;
import me.ibrahimsn.viewmodel.di.module.NetworkModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ActivityBindingModule.class, ViewModelModule.class})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    void inject(BaseApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        ApplicationComponent build();
    }
}