package app.api.Persistence.Entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "genre")
public class Genre
{
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "genreId",nullable=false)
  private Long id;
  @Column(name = "genreName",nullable=false)
  private String name;
  @ManyToMany(mappedBy = "genre")
  private Set<Book> books = new HashSet<>();

  public Genre() {
  }

  public Genre(String name) {
    this.name = name;
  }

  // Getters and Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Book> getBooks() {
    return books;
  }

  public void setBooks(Set<Book> books) {
    this.books = books;
  }
}





