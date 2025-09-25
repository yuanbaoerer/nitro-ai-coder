package org.example.nitroaicoder.ai;

import jakarta.annotation.Resource;
import org.example.nitroaicoder.ai.model.HtmlCodeResult;
import org.example.nitroaicoder.ai.model.MultiFileCodeResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AiCodeGeneratorServiceTest {
    @Resource
    private AiCodeGeneratorService aiCodeGeneratorService;

    @Test
    public void generateHtmlCode(){
        HtmlCodeResult result = aiCodeGeneratorService.generateHtmlCode("请做一个程序员 Nitro 的工作记录小工具");
        Assertions.assertNotNull(result);
    }

    @Test
    public void generateMultiFileCode(){
        MultiFileCodeResult result = aiCodeGeneratorService.generateMultiFileCode("请做一个程序员 Nitro 的留言板");
        Assertions.assertNotNull(result);
    }

}
