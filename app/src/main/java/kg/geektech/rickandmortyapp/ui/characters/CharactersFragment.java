package kg.geektech.rickandmortyapp.ui.characters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.rickandmortyapp.R;
import kg.geektech.rickandmortyapp.common.Resource;
import kg.geektech.rickandmortyapp.data.models.Character;
import kg.geektech.rickandmortyapp.data.models.RickAndMortyResponse;
import kg.geektech.rickandmortyapp.databinding.FragmentCharactersBinding;

@AndroidEntryPoint
public class CharactersFragment extends Fragment implements OnItemClickListener {
    private FragmentCharactersBinding binding;
    private CharacterViewModel viewModel;
    private CharactersAdapter adapter;
    private NavController controller;


    public CharactersFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new CharactersAdapter();
        viewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);
        adapter.setOnItemClickListener(this);
        viewModel.getCharacters();
        NavHostFragment hostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager()
                .findFragmentById(R.id.nav_host);
        controller = hostFragment.getNavController();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharactersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.rvCharacters.setAdapter(adapter);
        viewModel.liveData.observe(getViewLifecycleOwner(), resource -> {
            switch (resource.status) {
                case SUCCESS: {
                    binding.rvCharacters.setVisibility(View.VISIBLE);
                    binding.progressBar.setVisibility(View.GONE);
                    adapter.setCharacters(resource.data.getResults());
                    break;
                }
                case ERROR: {
                    binding.rvCharacters.setVisibility(View.GONE);
                    binding.progressBar.setVisibility(View.GONE);
                    Log.e("TAG", "onChanged: " + resource.message);
                    break;
                }
                case LOADING: {
                    binding.rvCharacters.setVisibility(View.GONE);
                    binding.progressBar.setVisibility(View.VISIBLE);
                    break;
                }
            }
        });
    }

    @Override
    public void onClick(Character character) {
        controller.navigate(CharactersFragmentDirections.
                actionCharactersFragmentToCharacterDetailFragment(character.getId()));
    }
}