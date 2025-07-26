package app.api.IntegrationTest;


import app.api.Persistence.DTOS.BookDTO;
import app.api.Persistence.DTOS.GenreDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Set;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class BookControllerTest { // Injects the MockMvc tool to simulate HTTP requests.
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // --- The Test Method ---

    @Test
    void addBook_whenPostRequest_thenCreatesBookAndReturns201() throws Exception {

        GenreDTO genre1 = new GenreDTO("Classic Literature");
        GenreDTO genre2 = new GenreDTO("American Literature");
        BookDTO newBook = new BookDTO(
                "The Great Gatsby",
                "F. Scott Fitzgerald",
                "Fiction",
                Set.of(genre1, genre2)
        );



        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON) // Set the Content-Type header
                        .content(objectMapper.writeValueAsString(newBook))) // Set the request body with our book object converted to JSON

                .andExpect(status().isCreated()) // We expect HTTP Status 201 Created
                .andExpect(jsonPath("$.bookTitle").value("The Great Gatsby")) // The "bookTitle" should match
                .andExpect(jsonPath("$.genre[0].name").value("Classic Literature")); // We can even check nested fields
    }
}
