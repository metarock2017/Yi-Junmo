package org.redrock.SendGet;

public class AccessToken {
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public int getExpiresin() {
        return expiresin;
    }
    public void setExpiresin(int expiresin) {
        this.expiresin = expiresin;
    }
    private String accessToken;
    private int expiresin;
}
