package app.api.Persistence.Entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "genre")
public class Genre extends BaseEntityAudit
{

  @Column(name = "genre_name",nullable=false)
  private String name;
  @ManyToMany(mappedBy = "genres")
  private Set<Book> books = new HashSet<>();

  public Genre() {
    super();
  }

  public Genre(String name) {
    this.name = name;
  }

  // Getters and Setters


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





