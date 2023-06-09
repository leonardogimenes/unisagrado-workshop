package com.example.demo.configuration

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.bson.UuidRepresentation
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(basePackages = ["com.example.demo.repository"])
class MongoDBConfiguration : AbstractMongoClientConfiguration() {

    override fun getDatabaseName() = "workshop"

    override fun mongoClient(): MongoClient {
        val clientSettings = MongoClientSettings.builder()
            .applyConnectionString(ConnectionString(""))
            .uuidRepresentation(UuidRepresentation.STANDARD)
            .build()

        return MongoClients.create(clientSettings)
    }
}
