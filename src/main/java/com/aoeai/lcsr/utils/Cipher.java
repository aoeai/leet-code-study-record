package com.aoeai.lcsr.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class Cipher {

    public final static String sha256(String str) {
        return DigestUtils.sha256Hex(str);
    }

    public final static String getSalt(){
        return sha256("" + System.currentTimeMillis());
    }

}
