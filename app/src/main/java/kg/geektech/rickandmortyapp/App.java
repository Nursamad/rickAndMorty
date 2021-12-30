package kg.geektech.rickandmortyapp;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;
import kg.geektech.rickandmortyapp.data.remote.RetrofitClient;
import kg.geektech.rickandmortyapp.data.remote.RickAndMortyApi;
import kg.geektech.rickandmortyapp.data.repository.MainRepository;

@HiltAndroidApp
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
