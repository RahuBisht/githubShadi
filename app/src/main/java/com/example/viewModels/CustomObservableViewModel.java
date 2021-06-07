package com.example.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.AndroidViewModel;


/**
 * The type Custom observable view model.
 */
public abstract class CustomObservableViewModel extends AndroidViewModel implements Observable {
   // private MutableLiveData<Integer> triggerEventToView = new MutableLiveData<>();
    private PropertyChangeRegistry mCallbacks;

    /**
     * Instantiates a new Custom observable view model.
     *
     * @param application the application
     */
   public CustomObservableViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callbackRemove) {
        synchronized (this) {
            if (mCallbacks == null) {
                return;
            }
        }
        mCallbacks.remove(callbackRemove);
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callbackAdd) {
        synchronized (this) {
            if (mCallbacks == null) {
                mCallbacks = new PropertyChangeRegistry();
            }
        }
        mCallbacks.add(callbackAdd);
    }

    /**
     * Notifies listeners that all properties of this instance have changed.
     */
    public void notifyChange() {
        synchronized (this) {
            if (mCallbacks == null) {
                return;
            }
        }
        mCallbacks.notifyCallbacks(this, 0, null);
    }

    /**
     * Notifies listeners that a specific property has changed. The getter for the property
     * that changes should be marked with {@link Bindable} to generate a field in
     * <code>BR</code> to be used as <code>fieldId</code>.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    public void notifyPropertyChanged(int fieldId) {
        synchronized (this) {
            if (mCallbacks == null) {
                return;
            }
        }
        mCallbacks.notifyCallbacks(this, fieldId, null);
    }

 /*   *//**
     * Gets close dialog.
     *
     * @return the close dialog
     *//*
    public LiveData<Integer> getTriggerEventToView() {
        return triggerEventToView;
    }

    *//**
     * Different types of action to be performed
     *
     * @param type the type
     *//*
    public void triggerEventToView(int type) {
        triggerEventToView.setValue(type);
    }*/

}
