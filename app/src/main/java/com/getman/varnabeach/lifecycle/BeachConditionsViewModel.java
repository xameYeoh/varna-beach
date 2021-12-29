package com.getman.varnabeach.lifecycle;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.getman.varnabeach.data.Repository;

import java.util.Map;

public class BeachConditionsViewModel extends AndroidViewModel {

    private final MutableLiveData<Map<String, String>> conditions;
    private final Repository repository;

    public BeachConditionsViewModel(Application application) {
        super(application);

        repository = Repository.getInstance(application);
        conditions = new MutableLiveData<>(repository.getBeachConditions());
    }

    public LiveData<Map<String, String>> getConditions() {
        return conditions;
    }

    public void requestConditions(String lat, String lng) {
        repository.requestNewConditions(lat, lng);
    }
}
