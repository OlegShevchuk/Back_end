package Back_end.service;

import Back_end.dao.ContactMapper;
import Back_end.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Олег on 24.02.2016.
 */

@Service
public class ContactService {

    @Autowired
    private ContactMapper contactMapper;


    /*
    * возвращает список всех элементов БД
    * */
    public List<Contact> findAllName() {
        return contactMapper.selectAllNames();
    }


    /*
    * добавляет элемент в БД
    *
    *
    * */
    public int addContact(Contact contact) {
        return contactMapper.creat(contact);
    }
    /*
    *установка лимита и вычисления количества элементов не входящих в ответ
    * */
    public List<Contact> calculationOfborders(String filter, Long limit, Long page) {
        this.limit=limit;
        startIndex=limit*(page-1);
        return selections(filter);
    }

    /*
    * подготовка фильтра
    * разбитие на диапазоны и пошаговые запросы фильтра
    *
    * */
    public List<Contact> selections(String filter) {
        List<Contact> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(filter);

        long d=contactMapper.findByMaxId()/limit+1;
        for (int j=0; j<d; j++) {
            if (searchRange(pattern, result, j).size()==limit) {
                return result;
            }
        }

        return result;
    }

    /*
    * startIndex-количество элементов не попадающих в результат
    */
    private long startIndex;

    /*
    * limit- размер диапазона и количество элементов в ответе
    */
    private long limit;

    /*
    * фильтрует элементы в заданом диапазоне
    *
    * j- порядковый номер диапазона
    *
    *
    * pattern- маска элементов которые не входят в ответ
    *
    * */
    private List<Contact> searchRange(Pattern pattern, List<Contact> result,  int j){

        for (Contact name : contactMapper.selectionFromRange(j * limit+1, (j + 1) * limit)) {
            if (!(pattern.matcher(name.getName()).matches())) {
                if (startIndex-- < 0) {
                    result.add(name);
                    if (result.size() == limit) {
                        return result;
                    }
                }

            }
        }
        return result;
        }



}
