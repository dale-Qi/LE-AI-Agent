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
        // ��һ��
        String message = "��ã����Ǵ���";
        String answer = writeApp.dochat(message, chatId);
        Assertions.assertNotNull(answer);
        // �ڶ���
        message = "����һ��С���鲩��������Ҫдһ�������ص�����";
        answer = writeApp.dochat(message, chatId);
        Assertions.assertNotNull(answer);
        // ������
        message = "�ҽ�ʲô���ţ��Ҹղź���˵��";
        answer = writeApp.dochat(message, chatId);
        Assertions.assertNotNull(answer);
    }
}