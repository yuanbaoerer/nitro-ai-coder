package org.example.nitroaicoder.core;

import jakarta.annotation.Resource;
import org.example.nitroaicoder.ai.AiCodeGeneratorService;
import org.example.nitroaicoder.model.enums.CodeGenTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
public class AiCodeGenerateFacadeTest {
    @Resource
    private AiCodeGeneratorFacade aiCodeGeneratorFacade;

    @Test
    void generateAndSaveCode(){
        File file = aiCodeGeneratorFacade.generateAndSaveCode("生成一个HTML页面，内容是“Hello World”", CodeGenTypeEnum.HTML);
        Assertions.assertNotNull(file);

    }
}
