package kg.geektech.rickandmortyapp.ui.characters;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kg.geektech.rickandmortyapp.App;
import kg.geektech.rickandmortyapp.common.Resource;
import kg.geektech.rickandmortyapp.data.models.Character;
import kg.geektech.rickandmortyapp.data.models.RickAndMortyResponse;
import kg.geektech.rickandmortyapp.data.repository.MainRepository;

@HiltViewModel
public class CharacterViewModel extends ViewModel {

    public LiveData<Resource<RickAndMortyResponse<Character>>> liveData;
private MainRepository repository;

    @Inject
    public CharacterViewModel(MainRepository repository) {
        this.repository = repository;
    }

    public void getCharacters(){
        liveData = repository.getCharacters();
    }
}
