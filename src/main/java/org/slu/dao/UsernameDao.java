package org.slu.dao;

import org.apache.ibatis.session.SqlSession;
import org.slu.pojo.Username;
import org.slu.pojo.UsernameMapper;
import org.slu.utils.MyBatisUtil;

import java.util.HashMap;
import java.util.Map;

public class UsernameDao {
    private static Map<String, String> usernameMap = new HashMap<>();
    public static String getUsernameByQqAcc(String qqAcc) {
        String resStr = "";
        if (usernameMap.containsKey(qqAcc)) {
            resStr = usernameMap.get(qqAcc);
        } else {
            SqlSession sqlSession = MyBatisUtil.getSqlSession();
            UsernameMapper mapper = sqlSession.getMapper(UsernameMapper.class);
            Username username = mapper.getNameByQqAcc(qqAcc);
            if(username == null) {
                resStr = qqAcc;
            } else {
                resStr = username.getUsername();
                usernameMap.put(qqAcc, resStr);
            }
            sqlSession.close();
        }
        return resStr;
    }

    public static void setUsername(String qqAcc, String name) {
        Username username = new Username(qqAcc, name);
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UsernameMapper mapper = sqlSession.getMapper(UsernameMapper.class);
        Username getusername = mapper.getNameByQqAcc(qqAcc);
        if(getusername == null) {
            mapper.insertUsername(username);
        } else {
            mapper.setName(username);
        }
        sqlSession.commit();
        sqlSession.close();
        if(usernameMap.containsKey(qqAcc)) {
            usernameMap.remove(qqAcc);
        }
        usernameMap.put(qqAcc, name);
    }
}
