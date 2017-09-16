package cn.longzzai.pojo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.List;

/**
 * 通用的result包装
 *
 * @author longcho
 * 2017-09-16-14:04
 */
@Data
public class ZzaiResult {
    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();
    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    public ZzaiResult() {
    }
    public ZzaiResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }
    public ZzaiResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static ZzaiResult ok() {
        return new ZzaiResult(null);
    }
    public static ZzaiResult ok(Object data) {
        return new ZzaiResult(data);
    }
    public static ZzaiResult build(Integer status, String msg ,Object data) {
        return new ZzaiResult(status, msg, data);
    }
    public static ZzaiResult build(Integer status, String msg) {
        return new ZzaiResult(status, msg, null);
    }
    /**
     * 将json结果集转化为TZzaiResult对象
     *
     * @param jsonData json数据
     * @param clazz ZzaiResult中的object类型
     * @return
     */
    public static ZzaiResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, ZzaiResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static ZzaiResult format(String json) {
        try {
            return MAPPER.readValue(json, ZzaiResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static ZzaiResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }
}
