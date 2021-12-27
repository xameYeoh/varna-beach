package com.getman.varnabeach.lifecycle;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.getman.varnabeach.data.Repository;
import com.getman.varnabeach.room.Beach;

import java.util.List;

public class BeachViewModel extends AndroidViewModel {
    private final Repository repository;
    private final LiveData<List<Beach>> allBeaches;

    public BeachViewModel(Application application) {
        super(application);

        repository = new Repository(application);
        allBeaches = repository.getAllBeaches();
    }

    public LiveData<List<Beach>> getAllBeaches() {
        return allBeaches;
    }

    /*public LiveData<List<Beach>> getConditions() {
        if (users == null) {
            users = new MutableLiveData<>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        // Do an asynchronous operation to fetch beaches.
    }*/
}
