package app.api.Service.mapper;


import app.api.Persistence.DTOS.BookDTO;
import app.api.Persistence.Entity.Book;
import app.api.Persistence.Entity.Genre;
import app.api.Persistence.Repo.GenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;

@Component
public class BookMapper {

    private final GenreMapper genreMapper;
    private final GenreRepo genreRepo;
    private final UserMapper userMapper;

@Autowired
  public BookMapper(GenreMapper genreMapper, GenreRepo genreRepo, UserMapper userMapper) {
        this.genreMapper = genreMapper;
        this.genreRepo = genreRepo;
        this.userMapper = userMapper;
    }


   public BookDTO toDto(Book book)
    {
        if(book == null)
            return null;
        BookDTO dto = new BookDTO();

        mapBookToBookDTO(book, dto);

        return dto;

    }

   public Book toEntity(BookDTO bookDto)
    {
        if(bookDto == null)
            return null;
        Book book = new Book();
        mapBookDtoToBook(bookDto,book);
        return book;

    }
    public void updateEntityFromDto(Book existingBook, BookDTO bookDto) {
        mapBookDtoToBook(bookDto,existingBook);
    }



    private void mapBookDtoToBook(BookDTO bookDto,Book book) {
        book.setAuthor(bookDto.getAuthor());
        book.setTitle(bookDto.getTitle());
        book.setCategory(bookDto.getCategory());
        book.setType(bookDto.getType());

        if (bookDto.getGenres() != null) {
            bookDto.getGenres().stream()
                    .map(genre -> genreRepo.findByName(genre.getName())
                            .orElseGet(() -> new Genre(genre.getName())))
                    .forEach(book::addGenre);
        }
    }
    private void mapBookToBookDTO(Book book,BookDTO dto) {
        dto.setId(book.getId());
        dto.setCreatedAt(book.getCreatedAt());
        dto.setUpdatedAt(book.getUpdatedAt());
        dto.setAuthor(book.getAuthor());
        dto.setTitle(book.getTitle());
        dto.setCategory(book.getCategory());
        dto.setImageUrl(book.getImageUrl());
        dto.setImagePath(book.getImagePath());
        dto.setType(book.getType());
        dto.setUser(userMapper.toDto(book.getUser()));
        dto.setGenres(
                book.getGenres().stream()
                        .map(genreMapper::toDto)
                        .collect(Collectors.toSet())
        );
    }
}
