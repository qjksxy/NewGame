package org.slu.dao;

import org.apache.ibatis.session.SqlSession;
import org.slu.pojo.UserChip;
import org.slu.pojo.UserChipMapper;
import org.slu.utils.MyBatisUtil;

public class UserChipDao {
    public static UserChip getUserChip(String qqAcc, int chipID) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserChipMapper mapper = sqlSession.getMapper(UserChipMapper.class);
        UserChip userChip = mapper.getUserChip(qqAcc, chipID);
        sqlSession.close();
        return userChip;
    }

    /**
     * 请务必判断为空再行添加
     * if (UserChipDao.getUserChip(userChip.qqAcc, userChip.chipID) == null )
     * @param userChip
     */
    public static void insertUserChip(UserChip userChip) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserChipMapper mapper = sqlSession.getMapper(UserChipMapper.class);
        mapper.insertUserChip(userChip);
        sqlSession.commit();
        sqlSession.close();
    }

    public static void updateUserChip(UserChip userChip) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserChipMapper mapper = sqlSession.getMapper(UserChipMapper.class);
        mapper.updateUserChip(userChip);
        sqlSession.commit();
        sqlSession.close();
    }

    public static UserChip[] getUserChipsByQqAcc(String qqAcc) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserChipMapper mapper = sqlSession.getMapper(UserChipMapper.class);
        UserChip[] userChips = mapper.getUserChipsByQqAcc(qqAcc);
        sqlSession.close();
        return userChips;
    }
}
