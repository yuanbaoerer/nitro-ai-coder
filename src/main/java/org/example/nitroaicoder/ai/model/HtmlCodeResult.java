package org.example.nitroaicoder.ai.model;

import dev.langchain4j.model.output.structured.Description;
import lombok.Data;

/**
 * 封装结构化输出的对象
 */
@Description("生成 HTML 代码文件的结果")
@Data
public class HtmlCodeResult {
    @Description("HTML 代码")
    private String htmlCode;

    @Description("生成代码的描述")
    private String description;
}
