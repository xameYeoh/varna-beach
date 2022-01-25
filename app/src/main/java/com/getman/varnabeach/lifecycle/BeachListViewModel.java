package com.getman.varnabeach.lifecycle;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.getman.varnabeach.data.Repository;
import com.getman.varnabeach.room.Beach;

import java.util.List;

public class BeachListViewModel extends AndroidViewModel {
    private final LiveData<List<Beach>> allBeaches;

    public BeachListViewModel(Application application) {
        super(application);

        Repository repository = Repository.getInstance(application);
        allBeaches = repository.getAllBeaches();
    }

    public LiveData<List<Beach>> getAllBeaches() {
        return allBeaches;
    }
}
