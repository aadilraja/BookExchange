package app.api.Persistence.mapper;


import app.api.Persistence.DTOS.BookDTO;
import app.api.Persistence.Entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = GenreMapper.class)
public interface BookMapper {

    BookDTO toDto(Book book);

    Book toEntity(BookDTO bookDto);
}