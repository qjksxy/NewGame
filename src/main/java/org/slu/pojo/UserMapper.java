package org.slu.pojo;

public interface UserMapper {
    User getUserByQqAcc(String qqAcc);
    int updateUser(User user);
    int insertUser(User user);
}
