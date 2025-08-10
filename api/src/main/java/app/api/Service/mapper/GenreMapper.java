package app.api.Service.mapper;

import app.api.Persistence.DTOS.GenreDTO;
import app.api.Persistence.Entity.Genre;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GenreMapper {

    public GenreDTO toDto(Genre genre) {
        if (genre == null) return null;

        GenreDTO dto = new GenreDTO();
        dto.setId(genre.getId());
        dto.setName(genre.getName());
        return dto;
    }

    public Genre toEntity(GenreDTO dto) {
        if (dto == null) return null;

        Genre genre = new Genre();
        genre.setId(dto.getId());  // optional — skip this if ID is DB-generated
        genre.setName(dto.getName());
        return genre;
    }

    public Set<GenreDTO> toDtoSet(Set<Genre> genres) {
        if (genres == null) return null;

        return genres.stream()
                .map(this::toDto)
                .collect(Collectors.toSet());
    }

    public Set<Genre> toEntitySet(Set<GenreDTO> genreDTOs) {
        if (genreDTOs == null) return null;

        return genreDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }
}
