package app.api.Persistence.DTOS;

public class GenreDTO {
    private Long id;
    private String name;

    public GenreDTO() {}

    public GenreDTO(String name) {
        this.name = name;
    }

   public GenreDTO(Long id, String name) {}



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


}