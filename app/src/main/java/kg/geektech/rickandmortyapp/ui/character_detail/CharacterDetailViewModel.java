package kg.geektech.rickandmortyapp.ui.character_detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kg.geektech.rickandmortyapp.common.Resource;
import kg.geektech.rickandmortyapp.data.models.Character;
import kg.geektech.rickandmortyapp.data.repository.MainRepository;

@HiltViewModel
public class CharacterDetailViewModel extends ViewModel {

    private MainRepository repository;
    public LiveData<Resource<Character>> liveData;

    @Inject
    public CharacterDetailViewModel(MainRepository repository) {
     this.repository = repository;
    }

    public void getCharacterById(int id){
        liveData = repository.getCharacterById(id);
    }
}
