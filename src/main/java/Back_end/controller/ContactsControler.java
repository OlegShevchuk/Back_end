package Back_end.controller;

import Back_end.entity.Contact;
import Back_end.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Олег on 26.02.2016.
 */

@RestController
@RequestMapping("/hello/contacts")
public class ContactsControler {

    @Autowired
    private ContactService contactService;


    /*
    * описывает запрос GET, для выборки контактов не удовлетворяющих требованиям
    *
    * nameFilter- обязательный парамерт, содержит маску элементов, которые надо исключить из выборки
    *
    * limit- максимальное количество элементов для возврата, необязательный параметр
    * если отсутствует позвращаются все элементы
    *
    * page-номер страницы результатов, если задан limit, в противном случае игнорируется.
    * Если limit задан, а page нет, то возвращается первая страница
    *
    * */
    @RequestMapping(method = RequestMethod.GET)
    public List<Contact> getPageofContactsByFilter(@RequestParam(value = "nameFilter") String filter,
                                                   @RequestParam(value = "limit", required = false) Integer limit,
                                                   @RequestParam(value = "page", required = false) Integer page) {


        if (limit == null) {
            return contactService.calculationOfborders(filter, 0, 0);
        }



        return contactService.calculationOfborders(filter, limit, page == null ? 1 : page);
    }


}
