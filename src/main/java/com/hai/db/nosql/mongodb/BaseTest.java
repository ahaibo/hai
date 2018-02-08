package com.hai.db.nosql.mongodb;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;

/**
 * Created by Administrator on 2018/1/4.
 */
public class BaseTest {

    private static final String HOST_27017 = "localhost";
    private static final String HOST_27017_USERNAME = "admin";
    private static final String HOST_27017_SOURCE = "test";
    private static final String HOST_27017_PASSWORD = "123456";
    private static final int PORT_27017 = 27017;

    MongoClient client;
    MongoDatabase database;
    MongoCredential credential;
    boolean auth;

    protected Block<Document> displayBlock = new Block<Document>() {
        @Override
        public void apply(Document document) {
            System.out.println(document.toJson());
        }
    };

    protected void initCredential() {
        credential = MongoCredential.createCredential(HOST_27017_USERNAME, HOST_27017_SOURCE, HOST_27017_PASSWORD.toCharArray());
    }

    //new instance mongoClient with authentication
    protected void auth() {
        auth = true;
        initCredential();
        ServerAddress address = new ServerAddress(HOST_27017, PORT_27017);
        MongoClientOptions mongoClientOptions = MongoClientOptions.builder().build();
        client = new MongoClient(address, credential, mongoClientOptions);
//        client = new MongoClient(address, Arrays.asList(credential));

        //或者使用连接字符串而不明确指定认证机制
//        MongoClientURI uri = new MongoClientURI("mongodb://user1:pwd1@host1/?authSource=db1");
//        MongoClient mongoClient = new MongoClient(uri);
    }

    @Before
    public void before() {
        client = new MongoClient(HOST_27017, PORT_27017);
        System.out.println("new instance MongoClient success!");

        database = client.getDatabase("test");
        System.out.println("get database test success!");
    }

    @After
    public void after() {
        database = null;
        client.close();
    }

    protected boolean existsCollection(String collName) {
        return null != database.getCollection(collName);
    }

}
