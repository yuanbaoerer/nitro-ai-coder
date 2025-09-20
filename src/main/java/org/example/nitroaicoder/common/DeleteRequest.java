package org.example.nitroaicoder.common;

import lombok.Data;

/**
 * 删除请求包装类，接受要删除数据的 id 作为参数
 */
@Data
public class DeleteRequest {

    private Long id;

    private static final long serialVersionUID = 1L;
}
