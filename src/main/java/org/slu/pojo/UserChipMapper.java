package org.slu.pojo;

public interface UserChipMapper {
    UserChip getUserChip(String qqAcc, int chipID);
    int updateUserChip(UserChip userChip);
    int insertUserChip(UserChip userChip);
    UserChip[] getUserChipsByQqAcc(String qqAcc);
}
