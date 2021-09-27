package org.slu.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Username {
    String qqAcc;
    String username;

    public String getQqAcc() {
        return qqAcc;
    }

    public void setQqAcc(String qqAcc) {
        this.qqAcc = qqAcc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Username{" +
                "qqAcc='" + qqAcc + '\'' +
                ", gameName='" + username + '\'' +
                '}';
    }
}
