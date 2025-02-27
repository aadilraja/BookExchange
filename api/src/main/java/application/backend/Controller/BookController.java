package application.backend.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BookController
{
    @GetMapping("/hello")
    public String printHello()
    {
        return "<h1>hello</h1>";
    }

}
