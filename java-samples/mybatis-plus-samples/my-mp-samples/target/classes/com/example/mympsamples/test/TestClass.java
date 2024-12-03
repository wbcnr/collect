package com.example.mympsamples.test;

import com.baomidou.mybatisplus.core.toolkit.AES;
import lombok.extern.slf4j.Slf4j;

/**
 * 随机秘钥的长度可以自己参考源码生成任意长度的；
 */
@Slf4j
public class TestClass {

    public static void main(String[] args) {
        String data = "password";
        // 生成16位随机AES密钥
        String randomKey = AES.generateRandomKey();
        // 使用随机密钥加密数据
        String encryptedData = AES.encrypt(data, randomKey);
        //输出随机秘钥、原始数据 和 加密后的数据
        log.info("明文:{},随机秘钥：{},密文:{}", data, randomKey, encryptedData);
        /**
         * 明文:password,
         * 随机秘钥：FfsjEu5ZRc9KefL3,
         * 密文:z4WWUYxz2OSdSPhqwTtNgg==
         * 设置方法：
         * Program arguments：
         * --mpw.key=FfsjEu5ZRc9KefL3
         */
    }
}
