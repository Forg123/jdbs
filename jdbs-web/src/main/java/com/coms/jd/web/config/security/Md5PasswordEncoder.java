package com.coms.jd.web.config.security;

import com.coms.jd.utils.encode.Md5Utils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Md5PasswordEncoder implements PasswordEncoder {
    private String userName;
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Md5PasswordEncoder() {
    }
    public Md5PasswordEncoder(String userName) {
        this.userName = userName;
    }
    @Override
    public String encode(CharSequence charSequence) {
        return Md5Utils.encode((String) charSequence , "");
    }
    @Override
    public boolean matches(CharSequence charSequence, String encodedPassword) {
        return encodedPassword.equals(Md5Utils.encode((String)charSequence , ""));
    }

    /**
     * 升级编码
     *
    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return false;
    }
     * */
}
