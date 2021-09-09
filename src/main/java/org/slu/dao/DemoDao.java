package org.slu.dao;

import org.apache.ibatis.session.SqlSession;
import org.slu.pojo.Demo;
import org.slu.pojo.DemoMapper;
import org.slu.utils.MyBatisUtil;

import java.util.List;

public class DemoDao {
    public static void insertDemo(String qqAcc, int goldCoin) {
        Demo demo = new Demo(qqAcc, goldCoin);
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        DemoMapper mapper = sqlSession.getMapper(DemoMapper.class);
        mapper.insertDemo(demo);
        sqlSession.commit();
        sqlSession.close();
    }

    public static List<Demo> getDemoList() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        DemoMapper mapper = sqlSession.getMapper(DemoMapper.class);
        List<Demo> demoList = mapper.getDemoList();
        return demoList;
    }
}
