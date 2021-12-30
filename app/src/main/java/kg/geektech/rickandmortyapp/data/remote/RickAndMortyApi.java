package kg.geektech.rickandmortyapp.data.remote;

import kg.geektech.rickandmortyapp.data.models.Character;
import kg.geektech.rickandmortyapp.data.models.RickAndMortyResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RickAndMortyApi {

    @GET("character")
    Call<RickAndMortyResponse<Character>> getCharacters();

    @GET("character/{id}")
    Call<Character> getCharacter(
            @Path("id") int id
    );
}
