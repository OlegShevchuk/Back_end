package Back_end;


import Back_end.service.ContactService;
import Back_end.temp.InsertDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello
 */

@SpringBootApplication
public class App implements CommandLineRunner {
    @Autowired
    private ContactService contactService;


    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }


    @Override
    public void run(String... strings) throws Exception {

        new InsertDB(contactService).insertElement(1_000_000);

    }
}
