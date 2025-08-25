package com.dale.leaiagent.app;


import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Component;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;

@Component
@Slf4j
public class WriteApp {

    public final ChatClient chatClient;

    /**
     * 初始提示词
     */
    private static final String SYSTEM_PROMPT = "扮演深耕自媒体写作领域的专家。开场向用户表明身份，告知用户可提供选题、文案润色和爆款文章策划支持。" +
            "围绕用户提供的素材或需求主动追问：如写作目标（涨粉、转化、曝光）、平台类型（公众号、知乎、小红书、抖音等）、目标读者画像。" +
            "在对话过程中不断深入了解用户意图，帮助明确文章主题、结构、风格和亮点。" +
            "提供专业写作建议，包括标题优化、开头吸引点、段落逻辑、语言风格、结尾号召等方面。" +
            "引导用户补充必要的背景信息和写作要求，确保生成的文案贴合目标读者和传播场景，给出可直接使用的成品建议。";

    /**
     * 初始化AI客户端
     * @param dashscopeChatModel
     */
    public WriteApp(ChatModel dashscopeChatModel){
        //初始化基于内存的对话记忆

        ChatMemory chatMemory = new InMemoryChatMemory();
        //使用构造函数初始化客户端对象
        chatClient = ChatClient.builder(dashscopeChatModel)
                .defaultSystem(SYSTEM_PROMPT)
                .defaultAdvisors(
                        new MessageChatMemoryAdvisor(chatMemory)
                )
                .build();

    }

    /**
     * AI基础对话，支持多轮对话记忆
     * @param message
     * @param chatId
     * @return
     */
    public String dochat(String message, String chatId){
        ChatResponse chatResponse = chatClient
                .prompt()
                .user(message)
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 5))
                .call()
                .chatResponse();
        String content = chatResponse.getResult().getOutput().getText();
        log.info("content: {}",content);
        return content;
    }
}
