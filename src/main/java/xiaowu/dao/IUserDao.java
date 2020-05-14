package xiaowu.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import xiaowu.domain.User;

import java.util.List;

public interface IUserDao {
    /*
    查询所有
     */
    @Select(value = "select * from user")
    List<User> findAll();

    /*
    保存用户
     */
    @Insert(value = "insert into user(id,username,address,sex,birthday) values(#{id},#{username},#{address},#{sex},#{birthday})")
    void saveUser(User user);

    /*
    更新用户信息
     */
    @Update(value = "update user set username=#{username},address=#{address},sex=#{sex},birthday=#{birthday} where id=#{id}")
    void updateUser(User user);

    /*
     根据id删除用户
     */
    @Delete(value = "delete from user where id=#{id}")
    void deleteUser(Integer id);
     /*
    根据用户名模糊查询
     */
     @Select(value = "select * from user where username like #{username}")
     List<User> findByName(String username);
}
