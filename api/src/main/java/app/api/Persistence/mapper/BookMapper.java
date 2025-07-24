package app.api.Persistence.mapper;


import app.api.Persistence.DTOS.BookDTO;
import app.api.Persistence.DTOS.GenreDTO;
import app.api.Persistence.Entity.Book;
import app.api.Persistence.Entity.Genre;
import app.api.Persistence.Repo.GenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    private final GenreMapper genreMapper;
    private final GenreRepo genreRepo;

@Autowired
  public BookMapper(GenreMapper genreMapper, GenreRepo genreRepo) {
        this.genreMapper = genreMapper;
        this.genreRepo = genreRepo;
    }


   public BookDTO toDto(Book book)
    {
        if(book == null)
            return null;
        BookDTO dto = new BookDTO();
        dto.setBookAuthor(book.getBookAuthor());
        dto.setBookTitle(book.getBookTitle());
        dto.setBookCategory(book.getBookCategory());
        dto.setGenre(
                book.getGenres().stream()
                        .map(genreMapper::toDto)
                        .collect(Collectors.toSet())
        );

        return dto;

    }

   public Book toEntity(BookDTO bookDto)
    {
        if(bookDto == null)
            return null;
        Book book = new Book();
        book.setBookAuthor(bookDto.getBookAuthor());
        book.setBookTitle(bookDto.getBookTitle());
        book.setBookCategory(bookDto.getBookCategory());

        if (bookDto.getGenre() != null) {
            bookDto.getGenre().stream()
                    .map(genre -> genreRepo.findByName(genre.getName())
                            .orElseGet(() -> new Genre(genre.getName())))
                    .forEach(book::addGenre);
        }


        return book;


    }
}