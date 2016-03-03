package Back_end.temp;

import Back_end.entity.Contact;
import Back_end.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * вспомагательный класс
 *
 * создаёт объкты и добавляет объекты в БД, генерируя случайные имена
 */

public class InsertDB {

    private ContactService contactService;
    private final Random RANDOM = new Random();

    public InsertDB(ContactService contactService) {
        this.contactService = contactService;
    }

    /*
        * отправляет созданые объекты в сервис для добавления в БД
         *
         * quantityOfElements- колличество генерируемых объектов
        * */
    public void insertElement(int quantityOfElements) {
        for (int i = 0; i < quantityOfElements; i++) {
            contactService.addContact(genegatorOfElement());
        }
    }

    /*
    * герерирует длину строка для создания объекта Contact
    *
    * возвращает созданый объект
    * */
    public Contact genegatorOfElement() {
        return new Contact(nameGenerator((RANDOM.nextInt(300) / 20 + 1)));
    }

    /*
    * генерирует случайный набор символов из латинского алфавита в нижнем регистре
    * испрользуется для инициализации поля name класса Contact
    * */
    public String nameGenerator(int length) {
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < length; i++) {
            name.append((char) (RANDOM.nextInt(26) + 97));
        }
        return name.toString();
    }
}
