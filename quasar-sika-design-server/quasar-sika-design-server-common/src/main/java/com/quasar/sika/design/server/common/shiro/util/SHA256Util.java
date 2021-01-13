package com.quasar.sika.design.server.common.shiro.util;

import com.quasar.sika.design.server.business.user.pojo.dto.UserDTO;
import com.quasar.sika.design.server.common.shiro.constant.ShiroConstant;
import org.apache.shiro.crypto.hash.SimpleHash;

public class SHA256Util {
    /**
     * 私有构造器
     **/
    private SHA256Util() {
    }

    public static String sha256(UserDTO userDTO) {
        return sha256(userDTO.getPassword(), userDTO.getUsername());
    }

    /**
     * 执行加密-采用SHA256和盐值加密
     **/
    public static String sha256(String password, String salt) {
        return new SimpleHash(ShiroConstant.HASH_ALGORITHM_NAME, password, salt, ShiroConstant.HASH_ITERATIONS).toString();
    }
}