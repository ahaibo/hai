package com.hai.desinpattern.create.prototype;

import java.io.*;

/**
 * 原型模式简单实现
 */
public class Prototype implements Cloneable, Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private SerializableObject serializableObject;

    /**
     * 浅拷贝：之复制基本数据类型；引用类型只复制器阴影地址
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Prototype clone() throws CloneNotSupportedException {
        return (Prototype) super.clone();
    }

    /**
     * 深复制
     *
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Prototype deepClone() throws IOException, ClassNotFoundException {
        int poolSize = 1024;
        ByteArrayOutputStream baos = new ByteArrayOutputStream(poolSize);
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(this);

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);

        return (Prototype) ois.readObject();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SerializableObject getSerializableObject() {
        return serializableObject;
    }

    public void setSerializableObject(SerializableObject serializableObject) {
        this.serializableObject = serializableObject;
    }
}
