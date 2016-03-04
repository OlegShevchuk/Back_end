package Back_end.dao;

import Back_end.entity.Contact;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * Created by Олег on 24.02.2016.
 */
public interface ContactMapper {


    @Insert("INSERT INTO contacts (id,name) VALUES(Null,#{name})")
    int creat(Contact entity);

    @Select("SELECT * FROM contacts")
    List<Contact> selectAllNames();

    @Select("SELECT * FROM contacts WHERE id = #{id}")
    Contact findById(long id);


    /*
    * выборка элементов из заданого диапазона
    * */
    @Select("SELECT * FROM contacts WHERE id  BETWEEN #{0} AND #{1}")
    List<Contact> selectionFromRange(long start, long finish);

    /*
    * получение максимального значения ID
    * */
    @Select("SELECT MAX(ID) FROM contacts")
    Long findByMaxId();

}
