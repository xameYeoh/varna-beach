package com.getman.varnabeach.lifecycle;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.getman.varnabeach.data.Repository;

import java.util.Map;

public class BeachConditionsViewModel extends AndroidViewModel {

    private MutableLiveData<Map<String, String>> conditions;
    private final Repository repository;

    public BeachConditionsViewModel(Application application) {
        super(application);

        repository = Repository.getInstance(application);
    }

    public LiveData<Map<String, String>> getConditions(String lat, String lng, OnLoadingListener listener) {
        if (conditions == null) {
            conditions = new MutableLiveData<>();
            listener.onLoading();
            requestConditions(lat, lng);
        }
        return conditions;
    }

    private void requestConditions(String lat, String lng) {
        repository.requestNewConditions(lat, lng, this::updateData);
    }

    private void updateData() {
        conditions.setValue(repository.getBeachConditions());
    }
}
