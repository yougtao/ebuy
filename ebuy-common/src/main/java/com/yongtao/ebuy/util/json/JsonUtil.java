package com.yongtao.ebuy.util.json;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.type.TypeFactory;

public class JsonUtil
{

    private static ObjectMapper mapper = new ObjectMapper();

    static {

        /**
         * 反序列化时忽略多余的属性
         */
        mapper.getDeserializationConfig().set(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        /**
         * 忽略Null的值,节省空间.
         */
        mapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        /**
         * 忽略Default值木有变化的属性,更节省空间,用于接收方有相同的Class
         * 如int属性初始值为0,那么这个属性将不会被序列化
         */
        mapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_DEFAULT);
    }

    /**
     * @param @param  jsonString
     * @param @return
     * @return boolean
     * @throws
     * @Title: isJSON
     * @author kaka
     * @Description: 判断 jsonString是否可以转换成json格式
     */
    public static boolean isJSON(String jsonString) {
        return mapper.canSerialize(HashMap.class);
    }

    public static <V> Map<String, V> toMap(String content, Class<? extends V> clazz) throws Exception {
        return mapper.readValue(content, TypeFactory.mapType(HashMap.class, String.class, clazz));
    }

    public static <V> Map<String, V> toMap(InputStream is, Class<? extends V> clazz) throws Exception {
        return mapper.readValue(is, TypeFactory.mapType(HashMap.class, String.class, clazz));
    }

    public static <V> Map<String, V> toMap(Reader is, Class<? extends V> clazz) throws Exception {
        return mapper.readValue(is, TypeFactory.mapType(HashMap.class, String.class, clazz));
    }

    public static <V> Map<String, V> toMap(URL is, Class<? extends V> clazz) throws Exception {
        return mapper.readValue(is, TypeFactory.mapType(HashMap.class, String.class, clazz));
    }


    public static <E> List<E> toList(String content, Class<? extends E> clazz) throws Exception {
        return jsonToList(content, clazz);
    }

    /**
     * @param @param  <E>
     * @param @param  content
     * @param @param  clazz
     * @param @return 元素类型为E的List
     * @param @throws Exception
     * @return List<E>
     * @throws
     * @Title: jsonToList
     * @author kaka
     * @Description: json转list , List的元素类型，会一并转换完成 如List<User>
     */
    public static <E> List<E> jsonToList(String content, Class<? extends E> clazz) throws Exception {
        return mapper.readValue(content, TypeFactory.collectionType(ArrayList.class, clazz));
    }

    /**
     * @param @param  content
     * @param @return
     * @param @throws Exception
     * @return Integer[]
     * @throws
     * @Title: jsonToIntArray
     * @author kaka
     * @Description: json转整形数组
     */
    public static Integer[] jsonToIntArray(String content) throws Exception {
        return jsonToArray(content, Integer.class);
    }

    public static Integer[] jsonToIntArray(String content, String key) throws Exception {
        return jsonToArray(content, key, Integer.class);
    }

    /**
     * @param @param  <E>
     * @param @param  content
     * @param @param  clazz 数组中的对象类型
     * @param @return E类型的数组，如User[]
     * @param @throws Exception
     * @return E[]
     * @throws
     * @Title: jsonToArray
     * @author kaka
     * @Description: json转对象数组
     */
    public static <E> E[] jsonToArray(String content, Class<? extends E> clazz) throws Exception {
        if (content != null) {
            return mapper.readValue(content, TypeFactory.arrayType(clazz));
        } else {
            return null;
        }
    }

    /**
     * @param @param  <T>
     * @param @param  content
     * @param @param  clazz 目标类型
     * @param @return
     * @param @throws Exception
     * @return T  返回类型为T的对象
     * @throws
     * @Title: fromJsonToObject
     * @author kaka
     * @Description: json转java对象，为兼容原util类
     */
    public static <T> T fromJsonToObject(String content, Class<? extends T> clazz) throws Exception {
        return jsonToObject(content, clazz);
    }

    public static <T> T jsonToObject(String content, Class<? extends T> clazz) throws Exception {
        return mapper.readValue(content, clazz);
    }

    /**
     * @param @param  <T>
     * @param @param  content json格式的字符串
     * @param @param  key 要转换的子json串的key
     * @param @param  clazz 目标类型
     * @param @return 返回类型为T的对象
     * @param @throws Exception
     * @return T
     * @throws
     * @Title: jsonToObject
     * @author kaka
     * @Description: 一个jsonStr包含多个java对象，取其中一个转化为java对象的方法
     */
    public static <T> T jsonToObject(String content, String key, Class<? extends T> clazz) throws Exception {
        JsonNode rootNode = mapper.readValue(content, JsonNode.class);
        JsonNode path = rootNode.path(key);
        if (!path.isMissingNode()) {
            return jsonToObject(path.toString(), clazz);
        } else {
            return null;
        }
    }

    public static Integer getInt(String content, String key) throws Exception {
        JsonNode rootNode = mapper.readValue(content, JsonNode.class);
        JsonNode path = rootNode.path(key);
        if (!path.isMissingNode()) {
            return jsonToObject(path.toString(), Integer.class);
        } else {
            return null;
        }
    }

    public static String getString(String content, String key) throws Exception {
        JsonNode rootNode = mapper.readValue(content, JsonNode.class);
        JsonNode path = rootNode.path(key);
        if (!path.isMissingNode()) {
            return jsonToObject(rootNode.path(key).toString(), String.class);
        } else {
            return null;
        }
    }

    public static Date getDate(String content, String key) throws Exception {
        JsonNode rootNode = mapper.readValue(content, JsonNode.class);
        JsonNode path = rootNode.path(key);
        if (!path.isMissingNode()) {
            return jsonToObject(path.toString(), Date.class);
        } else {
            return null;
        }
    }

    /**
     * 一个jsonStr包含多个java对象，将指定的key的json转化为对象数组的方法
     *
     * @param content 原始的json串
     * @param key     要转换的部分
     * @param clazz   目标类型
     * @return 目标类型的对象数组
     * @throws Exception
     */
    public static <E> E[] jsonToArray(String content, String key, Class<? extends E> clazz) throws Exception {
        JsonNode rootNode = mapper.readValue(content, JsonNode.class);
        JsonNode path = rootNode.path(key);
        if (!path.isMissingNode()) {
            return jsonToArray(rootNode.path(key).toString(), clazz);
        } else {
            return null;
        }
    }

    public static Integer[] jsonToArray(String content, String key) throws Exception {
        JsonNode rootNode = mapper.readValue(content, JsonNode.class);
        JsonNode path = rootNode.path(key);
        if (!path.isMissingNode()) {
            return jsonToArray(path.toString(), Integer.class);
        } else {
            return null;
        }
    }

    /**
     * @param @param  <E>
     * @param @param  content 原始的json串
     * @param @param  key 要转换的那部分json
     * @param @param  clazz 目标类型
     * @param @return 元素为目标类型的List
     * @param @throws Exception
     * @return List<E>
     * @throws
     * @Title: jsonToList
     * @author kaka
     * @Description: 一个jsonStr包含多个java对象，将指定的key的json转化为List<E>的方法
     */
    public static <E> List<E> jsonToList(String content, String key, Class<? extends E> clazz) throws Exception {
        JsonNode rootNode = mapper.readValue(content, JsonNode.class);
        JsonNode path = rootNode.path(key);
        if (!path.isMissingNode()) {
            return toList(path.toString(), clazz);
        } else {
            return null;
        }
    }

    /**
     * @param @param  o  要转换的对象
     * @param @return json格式的字符串
     * @param @throws Exception
     * @return String
     * @throws
     * @Title: toJson
     * @author kaka
     * @Description: 对象转化成json，已知问题 A a B b b中有a，a中有b ， 如果a和b同在一个o中将不能正常转化
     */
    public static String toJson(Object o) throws Exception {
        return mapper.writeValueAsString(o);
    }

    /**
     * @param @param  out
     * @param @param  o
     * @param @throws Exception
     * @return void
     * @throws
     * @Title: toJson
     * @author kaka
     * @Description: 转换成json串到out
     */
    public static void toJson(OutputStream out, Object o) throws Exception {
        mapper.writeValue(out, o);
    }

    /**
     * @param @param  out
     * @param @param  o
     * @param @throws Exception
     * @return void
     * @throws
     * @Title: toJson
     * @author kaka
     * @Description: 转换成json串到writer
     */
    public static void toJson(Writer out, Object o) throws Exception {
        mapper.writeValue(out, o);
    }

    public static String map2Json(Map map) throws Exception {
        return toJson(map);
    }

    /**
     * @param @param  json
     * @param @param  fillStringUnit
     * @param @return
     * @return String
     * @throws
     * @Title: formatJson
     * @author kaka
     * @Description: json字符串的格式化
     */
    public static String formatJson(String json, String fillStringUnit) {
        if (json == null || json.trim().length() == 0) {
            return null;
        }

        int fixedLenth = 0;
        ArrayList<String> tokenList = new ArrayList<String>();
        {
            String jsonTemp = json;
            //预读取
            while (jsonTemp.length() > 0) {
                String token = getToken(jsonTemp);
                jsonTemp = jsonTemp.substring(token.length());
                token = token.trim();
                tokenList.add(token);
            }
        }

        for (int i = 0; i < tokenList.size(); i++) {
            String token = tokenList.get(i);
            int length = token.getBytes().length;
            if (length > fixedLenth && i < tokenList.size() - 1 && tokenList.get(i + 1).equals(":")) {
                fixedLenth = length;
            }
        }

        StringBuilder buf = new StringBuilder();
        int count = 0;
        for (int i = 0; i < tokenList.size(); i++) {

            String token = tokenList.get(i);

            if (token.equals(",")) {
                buf.append(token);
                doFill(buf, count, fillStringUnit);
                continue;
            }
            if (token.equals(":")) {
                buf.append(" ").append(token).append(" ");
                continue;
            }
            if (token.equals("{")) {
                String nextToken = tokenList.get(i + 1);
                if (nextToken.equals("}")) {
                    i++;
                    buf.append("{ }");
                } else {
                    count++;
                    buf.append(token);
                    doFill(buf, count, fillStringUnit);
                }
                continue;
            }
            if (token.equals("}")) {
                count--;
                doFill(buf, count, fillStringUnit);
                buf.append(token);
                continue;
            }
            if (token.equals("[")) {
                String nextToken = tokenList.get(i + 1);
                if (nextToken.equals("]")) {
                    i++;
                    buf.append("[ ]");
                } else {
                    count++;
                    buf.append(token);
                    doFill(buf, count, fillStringUnit);
                }
                continue;
            }
            if (token.equals("]")) {
                count--;
                doFill(buf, count, fillStringUnit);
                buf.append(token);
                continue;
            }

            buf.append(token);
            //左对齐
            if (i < tokenList.size() - 1 && tokenList.get(i + 1).equals(":")) {
                int fillLength = fixedLenth - token.getBytes().length;
                if (fillLength > 0) {
                    for (int j = 0; j < fillLength; j++) {
                        buf.append(" ");
                    }
                }
            }
        }
        return buf.toString();
    }

    private static String getToken(String json) {
        StringBuilder buf = new StringBuilder();
        boolean isInYinHao = false;
        while (json.length() > 0) {
            String token = json.substring(0, 1);
            json = json.substring(1);

            if (!isInYinHao &&
                    (token.equals(":") || token.equals("{") || token.equals("}")
                            || token.equals("[") || token.equals("]")
                            || token.equals(","))) {
                if (buf.toString().trim().length() == 0) {
                    buf.append(token);
                }

                break;
            }

            if (token.equals("\\")) {
                buf.append(token);
                buf.append(json.substring(0, 1));
                json = json.substring(1);
                continue;
            }
            if (token.equals("\"")) {
                buf.append(token);
                if (isInYinHao) {
                    break;
                } else {
                    isInYinHao = true;
                    continue;
                }
            }
            buf.append(token);
        }
        return buf.toString();
    }

    private static void doFill(StringBuilder buf, int count, String fillStringUnit) {
        buf.append("\n");
        for (int i = 0; i < count; i++) {
            buf.append(fillStringUnit);
        }
    }
}