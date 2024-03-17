package com.game;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.inc;
import static com.mongodb.client.model.Updates.set;

public class DataBase {
    public static MongoClient mongoClient;

    private static MongoCollection<Document> collection;

    public static void createConnection() {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.WARNING);
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("Undertale");
        collection = database.getCollection("Ranking");
    }

    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

    public static void updatePlayerStats(String name, int score, Duration durationPlayed, boolean isWon) {
        long secondsSum = durationPlayed.getSeconds();
        createConnection();
        Document query = collection.find(eq("name", name)).first();
        String format = String.format("%02d:%02d:%02d", secondsSum / 3600, (secondsSum % 3600) / 60, secondsSum % 60);

        if (query == null) {
            Document document = (isWon) ?
                new Document("name", name)
                    .append("amount played", 1)
                    .append("amount won", 1)
                    .append("score", score)
                    .append("time played" , format)
                    .append("win rate", 100.0) :
                new Document("name", name)
                    .append("amount played", 1)
                    .append("amount won", 0)
                    .append("score", score)
                    .append("time played" , format)
                    .append("win rate", 0.0);
            collection.insertOne(document);
        } else {
            int actualScore = query.getInteger("score");
            int actualAmountWon = isWon? query.getInteger("amount won") + 1 : query.getInteger("amount won");

            int amountPlayed = query.getInteger("amount played") + 1;
            Bson update = combine(
                inc("amount played", 1),
                score > actualScore ? set("score", score) : set("score", actualScore),
                set("amount won", actualAmountWon),
                set("time played", format),
                set("win rate",  Math.round((float) actualAmountWon / amountPlayed * 100 * 100.0) / 100.0  )
            );
            collection.findOneAndUpdate(
                eq("name", name),
                update
            );
        }
    }

    public static List<Document> getRanking() {
        if (mongoClient == null) {
            createConnection();
        }

        List<Document> topTenScores;

        topTenScores = collection.find()

            .sort(Sorts.ascending("time played"))
            .sort(Sorts.descending("amount played"))
            .sort(Sorts.descending("win rate"))
            .sort(Sorts.descending("score"))

            .limit(10)
            .into(new ArrayList<>());
        return topTenScores;
    }
}
