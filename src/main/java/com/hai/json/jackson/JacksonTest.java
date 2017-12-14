package com.hai.json.jackson;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.hai.model.Person;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/20.
 */
public class JacksonTest {
    private Person person;
    private ObjectMapper mapper;
    private List<Person> persons;
    private Map<String, Person> personMap;
    private String personString;
    private String personListString;
    private String mapString;

    @Before
    public void before() {
        person = new Person();
        person.setId(1);
        person.setName("hai");

        persons = new ArrayList<>();
        persons.add(person);

        personMap = new HashMap<>();
        personMap.put(person.toString(), person);

        mapper = new ObjectMapper();
        //在反序列化时忽略在 json 中存在但 Java 对象不存在的属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //在序列化时日期格式默认为 yyyy-MM-dd'T'HH:mm:ss.SSSZ
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //在序列化时忽略值为 null 的属性
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //忽略值为默认值的属性
        mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_DEFAULT);
        //属性可视化设置
        //mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//        属性可视化
//        是 java 对象的所有的属性都被序列化和反序列化，换言之，不是所有属性都可视化，默认的属性可视化的规则如下：
//        若该属性修饰符是 public，该属性可序列化和反序列化。
//        若属性的修饰符不是 public，但是它的 getter 方法和 setter 方法是 public，该属性可序列化和反序列化。因为 getter 方法用于序列化， 而 setter 方法用于反序列化。
//        若属性只有 public 的 setter 方法，而无 public 的 getter 方 法，该属性只能用于反序列化。
//        若想更改默认的属性可视化的规则，需要调用 ObjectMapper 的方法 setVisibility。

//        属性过滤
//        注解方式， 可以用 @JsonIgnore 过滤单个属性或用 @JsonIgnoreProperties 过滤多个属性
        //更多配置信息可以查看 Jackson 的 DeserializationFeature，SerializationFeature 和 Include。
    }

    @Test
    public void writePersonString() throws IOException {
        personString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(person);
        System.out.println("write personString:\n" + personString);
    }

    @Test
    public void writePersonListString() throws IOException {
        personListString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(persons);
        System.out.println("write personListString:\n" + personListString);
    }

    @Test
    public void writeMapString() throws IOException {
        mapString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(personMap);
        System.out.println("write mapString:\n" + mapString);
    }

    @Test
    public void write() throws IOException {
        writePersonString();
        writePersonListString();
        writeMapString();
    }

    @Test
    public void read() throws IOException {
        writePersonString();
        //反序列化
        Person p = mapper.readValue(personString, Person.class);
        System.out.println(p);
    }

    //泛型测试
    @Test
    public void testGeneric() throws IOException {
        writePersonListString();
        CollectionType javaType = mapper.getTypeFactory().constructCollectionType(List.class, Person.class);
        List<Person> personList = mapper.readValue(personListString, javaType);
        List<Person> personList2 = mapper.readValue(personListString, new TypeReference<List<Person>>() {
        });
        System.out.println("testGeneric personListString1:\n" + JSONObject.toJSONString(personList));
        System.out.println("testGeneric personListString2:\n" + JSONObject.toJSONString(personList2));
    }

    @Test
    public void testMap() throws IOException {
        writeMapString();
        //第二参数是 map 的 key 的类型，第三参数是 map 的 value 的类型
        MapType javaType = mapper.getTypeFactory().constructMapType(HashMap.class, String.class, Person.class);
        Map<String, Person> personMap1 = mapper.readValue(mapString, javaType);
        Map<String, Person> personMap2 = mapper.readValue(mapString, new TypeReference<Map<String, Person>>() {
        });
        System.out.println("testMap personMap1:\n" + JSONObject.toJSONString(personMap1));
        System.out.println("testMap personMap2:\n" + JSONObject.toJSONString(personMap2));
        //Array 和 Collection 的处理与 List，Map 相似
    }

    @Test
    public void testNode() throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
        //构建 ObjectNode
        ObjectNode personNode = mapper.createObjectNode();
        //添加/更改属性
        personNode.put("name", "Tom");
        personNode.put("age", 40);
        ObjectNode addressNode = mapper.createObjectNode();
        addressNode.put("zip", "000000");
        addressNode.put("street", "Road NanJing");
        //设置子节点
        personNode.set("address", addressNode);
        //通过 path 查找节点
        JsonNode searchNode = personNode.path("street ");
        //删除属性
        ((ObjectNode) personNode).remove("address");
        //读取 json
        JsonNode rootNode = mapper.readTree(personNode.toString());
        //JsonNode 转换成 java 对象
        Person person = mapper.treeToValue(personNode, Person.class);
        //java 对象转换成 JsonNode
        JsonNode node = mapper.valueToTree(person);
        System.out.println(node.toString());
    }
}
