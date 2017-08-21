package org.redrock.misson1.jdbc.resultset;

import org.redrock.misson1.util.B64Util;

public class User {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) throws Exception {
        byte[] Passwd = B64Util.strToByteArray(passwd);
        passwd = B64Util.encode(Passwd);
        this.passwd = passwd;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String username;
    private String passwd;
    private String answer;
}
