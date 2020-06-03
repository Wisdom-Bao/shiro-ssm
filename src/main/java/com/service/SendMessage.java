package com.service;

import java.util.Map;

/**
 * @Author WisdomBao
 * @Date 2020/5/16 22:29
 * @Version 1.0
 */
public interface SendMessage {
    public boolean send(String phoneNumber, String templateCode, Map<String, Object> code);
}
