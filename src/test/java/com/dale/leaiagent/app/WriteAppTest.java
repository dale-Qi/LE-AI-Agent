package com.dale.leaiagent.app;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;


@SpringBootTest
class WriteAppTest {

    @Resource
    private WriteApp writeApp;
    @Test
    void testchat() {
        String chatId = UUID.randomUUID().toString();
        // 第一轮
        String message = "你好，我是大乐";
        String answer = writeApp.dochat(message, chatId);
        Assertions.assertNotNull(answer);
        // 第二轮
        message = "我是一个小红书博主，我需要写一个情感相关的帖子";
        answer = writeApp.dochat(message, chatId);
        Assertions.assertNotNull(answer);
        // 第三轮
        message = "我叫什么来着？我刚才和你说过";
        answer = writeApp.dochat(message, chatId);
        Assertions.assertNotNull(answer);
    }
}