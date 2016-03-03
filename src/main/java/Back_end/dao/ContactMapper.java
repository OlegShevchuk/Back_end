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


}
