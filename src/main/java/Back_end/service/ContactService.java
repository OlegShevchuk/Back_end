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
    *
    *
    * */
    public int addContact(Contact contact) {
            return contactMapper.creat(contact);
    }
    /*
    *
    * */
    public List<Contact> calculationOfborders(String filter, int limit, int page) {

        int startIndex=0;
        int finishIndex=Integer.MAX_VALUE;
        if (limit>0&&page>0){
            startIndex=limit*(page-1);
            finishIndex=limit*page;
        }
        return selections(filter,startIndex,finishIndex);
    }

    /*
    * выборка элементов из листа
    *
    * */
    private List<Contact> selections(String filter, int stastIndex, int finishIndex) {
        List<Contact> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(filter);
        int i=0;
        for (Contact name : contactMapper.selectAllNames()) {
            if (!(pattern.matcher(name.getName()).matches())) {
                if (stastIndex<=i&&finishIndex>i){
                    result.add(name);
                    if (i==finishIndex) {
                        return result;
                    }
                }
                i++;
            }
        }
        return result;

    }


}
