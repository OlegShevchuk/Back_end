package Back_end;

import Back_end.entity.Contact;
import Back_end.service.ContactService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * синхронизированый силгентон
 *
 * временное хранилище
 *
 * добавлен для увеличения скорости выборки
 *
 * создано для сокращения времени фильтрации более чем в 10 раз
 *
 * затраты оперативной памяти на содержание 1000 000 элементов контакт с длинной имени до 30 символов от 600Mb до 800Mb
 *
 *
 */

public class Storage {


    private List<Contact> storagName;

    private static Storage storage;

    private Storage(ContactService contactService) {
        storagName = contactService.findAllName();
    }

    public static synchronized Storage getInstance(ContactService contactService) {
        if (storage == null) {

            storage = new Storage(contactService);
        }
        return storage;
    }

    public List<Contact> getStoragList() {
        return storagName;
    }
}
