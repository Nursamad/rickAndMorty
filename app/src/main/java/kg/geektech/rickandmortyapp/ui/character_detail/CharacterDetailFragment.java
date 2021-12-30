package kg.geektech.rickandmortyapp.ui.character_detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.rickandmortyapp.R;
import kg.geektech.rickandmortyapp.common.Resource;
import kg.geektech.rickandmortyapp.data.models.Character;
import kg.geektech.rickandmortyapp.databinding.FragmentCharacterDetailBinding;

@AndroidEntryPoint
public class CharacterDetailFragment extends Fragment {
    private FragmentCharacterDetailBinding binding;
    private CharacterDetailFragmentArgs args;
    private CharacterDetailViewModel viewModel;

    public CharacterDetailFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = CharacterDetailFragmentArgs.fromBundle(getArguments());
        viewModel = new ViewModelProvider(requireActivity()).get(CharacterDetailViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false);
        binding.characterId.setText(String.valueOf(args.getCharacterId()));
        viewModel.getCharacterById(args.getCharacterId());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.liveData.observe(getViewLifecycleOwner(), new Observer<Resource<Character>>() {
            @Override
            public void onChanged(Resource<Character> characterResource) {
                switch (characterResource.status) {
                    case SUCCESS: {
                        Character character = characterResource.data;
                        binding.characterId.setText(character.getName());
                        binding.characterStatus.setText(character.getStatus());
                        Glide.with(binding.getRoot()).load(characterResource)
                                .load(character.getImage())
                                .centerCrop()
                                .into(binding.characterImage);
                        onSuccessState();
                        break;
                    }
                    case ERROR: {
                        onSuccessState();
                        Log.e("TAG", "onChanged: " + characterResource.message);
                        break;
                    }
                    case LOADING: {
                            onLoadingState();
                        break;

                    }
                }
            }
        });
    }
    private  void onLoadingState(){
        binding.characterStatus.setVisibility(View.GONE);
        binding.characterImage.setVisibility(View.GONE);
        binding.characterId.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private  void onSuccessState(){
        binding.characterStatus.setVisibility(View.VISIBLE);
        binding.characterImage.setVisibility(View.VISIBLE);
        binding.characterId.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.GONE);
    }
}