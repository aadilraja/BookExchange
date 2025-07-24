package app.api.Persistence.mapper;

import app.api.Persistence.DTOS.GenreDTO;
import app.api.Persistence.Entity.Genre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDTO toDto(Genre genre);

    Genre toEntity(GenreDTO genreDto);
}