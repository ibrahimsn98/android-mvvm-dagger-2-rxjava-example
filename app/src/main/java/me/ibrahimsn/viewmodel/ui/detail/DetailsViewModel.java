package me.ibrahimsn.viewmodel.ui.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Bundle;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import me.ibrahimsn.viewmodel.data.model.Repo;
import me.ibrahimsn.viewmodel.data.rest.RepoService;

public class DetailsViewModel extends ViewModel {

    private final RepoService repoService;
    private CompositeDisposable disposable;

    private final MutableLiveData<Repo> selectedRepo = new MutableLiveData<>();

    public LiveData<Repo> getSelectedRepo() {
        return selectedRepo;
    }

    @Inject
    public DetailsViewModel(RepoService repoService) {
        this.repoService = repoService;
        disposable = new CompositeDisposable();
    }

    public void setSelectedRepo(Repo repo) {
        selectedRepo.setValue(repo);
    }

    public void saveToBundle(Bundle outState) {
        if(selectedRepo.getValue() != null) {
            outState.putStringArray("repo_details", new String[] {
                    selectedRepo.getValue().owner.login,
                    selectedRepo.getValue().name
            });
        }
    }

    public void restoreFromBundle(Bundle savedInstanceState) {
        if(selectedRepo.getValue() == null) {
            if(savedInstanceState != null && savedInstanceState.containsKey("repo_details")) {
                loadRepo(savedInstanceState.getStringArray("repo_details"));
            }
        }
    }

    private void loadRepo(String[] repo_details) {
        disposable.add(repoService.getRepo(repo_details[0], repo_details[1]).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<Repo>() {
            @Override
            public void onSuccess(Repo value) {
                selectedRepo.setValue(value);
            }

            @Override
            public void onError(Throwable e) {

            }
        }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
