package kg.geektech.rickandmortyapp.ui.characters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import kg.geektech.rickandmortyapp.data.models.Character;
import kg.geektech.rickandmortyapp.data.models.RickAndMortyResponse;
import kg.geektech.rickandmortyapp.databinding.ItemCharacterBinding;

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder> {
    private ItemCharacterBinding binding;
    private List<Character> characters;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public CharactersAdapter() {
        characters = new ArrayList<>();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCharacters(List<Character> characters) {
        this.characters = characters;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CharacterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        holder.onBind(characters.get(position));
        holder.itemView.setOnClickListener(v->{
onItemClickListener.onClick(characters.get(holder.getAdapterPosition()));
        });
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }




    protected class CharacterViewHolder extends RecyclerView.ViewHolder {
        private ItemCharacterBinding binding;

        public CharacterViewHolder(@NonNull ItemCharacterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Character character) {
         binding.tvName.setText(character.getName());
         binding.tvStatus.setText(character.getStatus());
         Glide.with(binding.ivCharacter).load(character.getImage())
                 .centerCrop()
                 .into(binding.ivCharacter);
        }
    }
}
