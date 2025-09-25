package org.example.nitroaicoder.core;

import jakarta.annotation.Resource;
import org.example.nitroaicoder.ai.AiCodeGeneratorService;
import org.example.nitroaicoder.ai.model.HtmlCodeResult;
import org.example.nitroaicoder.ai.model.MultiFileCodeResult;
import org.example.nitroaicoder.exception.BusinessException;
import org.example.nitroaicoder.exception.ErrorCode;
import org.example.nitroaicoder.model.enums.CodeGenTypeEnum;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 使用 门面模式，方便统一管理生成和保存
 * 门面模式通过提供一个统一的高层接口来隐藏子系统的复杂性，让客户端只需要与这个简化的接口交互，而不需要了解内部的复杂实现细节
 */
@Service
public class AiCodeGeneratorFacade {
    @Resource
    private AiCodeGeneratorService aiCodeGeneratorService;


    /**
     * 统一入口：根据类型生成并保存代码
     * @param userMessage 用户提示词
     * @param codeGenTypeEnum 生成类型
     * @return 保存的目录
     */
    public File generateAndSaveCode(String userMessage, CodeGenTypeEnum codeGenTypeEnum){
        if(codeGenTypeEnum == null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "生成类型为空");
        }
        return switch (codeGenTypeEnum){
            case HTML -> generateAndSaveHtmlCode(userMessage);
            case MULTI_FILE -> generateAndSaveMultiFileCode(userMessage);
            default -> {
                String errorMessage = "不支持的生成类型：" + codeGenTypeEnum.getValue();
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, errorMessage);
            }
        };
    }

    /**
     * 生成 HTML 模式代码并保存
     * @param userMessage 用户提示词
     * @return 保存的目录
     */
    private File generateAndSaveHtmlCode(String userMessage){
        HtmlCodeResult result = aiCodeGeneratorService.generateHtmlCode(userMessage);
        return CodeFileSaver.saveHtmlCodeResult(result);
    }

    /**
     * 生成多文件模式的代码并保存
     * @param userMessage 用户提示词
     * @return  保存的目录
     */
    private File generateAndSaveMultiFileCode(String userMessage){
        MultiFileCodeResult result = aiCodeGeneratorService.generateMultiFileCode(userMessage);
        return CodeFileSaver.saveMultiFileCodeResult(result);
    }
}
