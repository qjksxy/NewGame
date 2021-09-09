package org.slu.pojo;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slu.utils.MyBatisUtils;

import java.util.List;

public class DemoTest {

    public void test() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        DemoMapper mapper = sqlSession.getMapper(DemoMapper.class);
        List<Demo> demoList = mapper.getDemoList();
        for (Demo demo : demoList) {
            System.out.println(demo);
        }
        sqlSession.close();

    }

    public void insertTest() {
        String qqAcc = "8888";
        int goldCoin = 8888;
        Demo demo = new Demo(qqAcc, goldCoin);
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        DemoMapper mapper = sqlSession.getMapper(DemoMapper.class);
        mapper.insertDemo(demo);
        sqlSession.commit();
        sqlSession.close();
    }
}
