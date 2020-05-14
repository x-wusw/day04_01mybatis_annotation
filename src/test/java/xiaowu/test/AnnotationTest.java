package xiaowu.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import xiaowu.dao.IUserDao;
import xiaowu.domain.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class AnnotationTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao iUserDao;

    @Before
    public void init() throws IOException {
         in = Resources.getResourceAsStream("SqlMapConfig.xml");
         SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
         sqlSession = factory.openSession();
         iUserDao = sqlSession.getMapper(IUserDao.class);

   }
   @After
   public void destory() throws IOException {
       in.close();
       sqlSession.commit();
       sqlSession.close();

   }
   /*
   查询所有用户信息
    */
   @Test
   public void findAll(){
       List<User> users = iUserDao.findAll();
       for(User user:users) {
           System.out.println(user);
       }

   }
    /*
    保存新用户信息
     */
   @Test
   public void saveUser(){
        User user = new User();
        user.setId(42);
        user.setUsername("阿武");
        user.setAddress("中国北京");
        user.setSex("男");
        user.setBirthday(new Date());
        iUserDao.saveUser(user);

   }
   /*
    更新用户信息
     */
   @Test
    public void updateUser(){
    User user = new User();
    user.setId(46);
    user.setUsername("小顺");
    user.setSex("男");
    user.setBirthday(new Date(19930908));
    user.setAddress("中国湖北");
    iUserDao.updateUser(user);
   }
   /*
    删除用户信息
     */
    @Test
    public void deleteUser(){
        iUserDao.deleteUser(52);

    }
    /*
    根据用户名模糊查询
     */
    @Test
    public void findByName(){
        List<User> users = iUserDao.findByName("%小%");
        for(User user:users){
            System.out.println(user);
        }
    }
}
