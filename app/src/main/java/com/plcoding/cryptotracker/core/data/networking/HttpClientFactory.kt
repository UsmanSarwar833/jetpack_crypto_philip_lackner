package com.plcoding.cryptotracker.core.data.networking

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


///implementation of http client later we use it to fetch the coin
object HttpClientFactory {

    fun create(engine:HttpClientEngine): HttpClient {
          return HttpClient(engine){
               install(Logging){
                   ///log everything of request and response
                   level = LogLevel.ALL
                   logger = Logger.ANDROID
               }
              ///ContentNegotiation -> ktor can automatically power json response to our kotlin dataclass later on ,with out us having to do us automatically
              install(ContentNegotiation){
                     json(
                         json = Json {
              ///ignoreunknownkeys -> if there is any unknown field of the  json response that we didn't declare in our
                                      // data class, we will simply ignore those field rather than making our app crash.
                             ignoreUnknownKeys = true
                         }
                     )
              }

              defaultRequest {
                  contentType(ContentType.Application.Json)///proper header add for each and every request
              }
          }
    }

}