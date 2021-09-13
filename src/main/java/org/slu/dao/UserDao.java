package org.slu.dao;

import org.apache.ibatis.session.SqlSession;
import org.slu.pojo.User;
import org.slu.pojo.UserMapper;
import org.slu.utils.MyBatisUtil;

public class UserDao {
    public static User getUserByQqAcc(String qqAcc) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserByQqAcc(qqAcc);
        sqlSession.close();
        return user;
    }

    public static int updateUser(User user) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int i = mapper.updateUser(user);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }
}
