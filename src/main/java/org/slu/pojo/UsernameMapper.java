package org.slu.pojo;

public interface UsernameMapper {
    Username getNameByQqAcc(String qqAcc);
    int setName(Username username);
    int insertUsername(Username username);
}
