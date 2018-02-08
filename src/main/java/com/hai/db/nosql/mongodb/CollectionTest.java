package com.hai.db.nosql.mongodb;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.client.ListCollectionsIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.InsertManyOptions;
import org.apache.commons.collections.map.HashedMap;
import org.bson.BsonArray;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Test;

import java.util.*;

/**
 * Created by Administrator on 2018/1/4.
 */
public class CollectionTest extends BaseTest {

    MongoCollection collection;
    private String collectionName = "col";

    @Test
    public void listCollections() {
        System.out.println(this.getClass().getName() + ".listCollections...");
        ListCollectionsIterable<Document> listCollectionsIterable = database.listCollections();
        cursorResult(listCollectionsIterable.iterator());
    }

    private <T> void cursorResult(MongoCursor<T> cursor) {
        while (cursor.hasNext()) {
            Document document = (Document) cursor.next();
            Set<Map.Entry<String, Object>> entries = document.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
//                System.out.println("key: " + entry.getKey());
//                System.out.println("value: " + entry.getValue());
                System.out.println(JSONObject.toJSONString(entry));
            }
            System.out.println();
        }
    }

    @Test
    public void collection() {
//        DBObject dbObject = new BasicDBObject();
//        dbObject.put("", "");

//        CreateCollectionOptions createCollectionOptions=new CreateCollectionOptions();
        boolean existCollection = existsCollection(collectionName);
        if (!existCollection) {
            database.createCollection(collectionName);
        }

        collection = database.getCollection(collectionName);
//        collection.createIndex(new BsonDocument("", new BsonString("")));
        System.out.println("collection 'col' full name: " + collection.getNamespace().getFullName());

//        FindIterable<Document> findIterable = collection.find();
//        System.out.println("FindIterable test...");
//        cursorResult(findIterable.iterator());

        collection.find().forEach(displayBlock);
    }

    @Test
    public void save() {
        collection();

//        ClientSession clientSession = new ClientSessionImpl(null, null, null);

        InsertManyOptions insertManyOptions = new InsertManyOptions();
        insertManyOptions.ordered(false);
        insertManyOptions.bypassDocumentValidation(false);

        List<Document> documents = new ArrayList<>();
        Document document = new Document();
        Map<String, Object> map = new HashedMap();
        map.put("id", 10000);
        map.put("name", "hai");
        map.put("sex", "man");
        map.put("age", 29);

        List<BsonValue> hobbyValues = Arrays.asList(new BsonString("film"), new BsonString("music"));
        BsonArray hobby = new BsonArray(hobbyValues);
        map.put("hubby", new BsonArray(hobby));
        map.put("modify", new Date());

        BasicDBObject basicDBObject = new BasicDBObject("title", "MongoDB");
        basicDBObject.append("description", "database");
        basicDBObject.append("likes", 100);
        basicDBObject.append("url", "//www.w3cschool.cn/mongodb/");
        basicDBObject.append("author", "hai");
        basicDBObject.append("modify", new Date());

        document.putAll(map);
        documents.add(document);

//        collection.insertMany(clientSession, documents, insertManyOptions);
        collection.insertOne(new Document(basicDBObject));
        collection.insertMany(documents);

        //update
        Bson filter = new Document();
        Bson updater = new Document();
//        collection.updateOne(filter, updater);

        //delete
//        collection.deleteOne(new Document()).getDeletedCount();

        System.out.println("collection insertMany documents successfully!");

//        listCollections();
    }

    public void mapreduce() {
        collection = database.getCollection(collectionName);

//        collection.mapReduce()
    }
}
