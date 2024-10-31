package com.plcoding.cryptotracker.core.data.networking

import com.plcoding.cryptotracker.core.domain.util.NetworkError
import com.plcoding.cryptotracker.core.domain.util.Result
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext


///these catch block catch error that happen before we can even get the response from the server
suspend inline fun <reified T> safeCall(execute: () -> HttpResponse): Result<T,NetworkError>{
   val response = try {
        execute()
    }catch(e:UnresolvedAddressException){
    ///UnresolvedAddressException means client is unable to resolve the address for the backend,it commonly thrown when client is not connected with the internet
       return  Result.Error(NetworkError.NO_INTERNET)
    }catch (e:SerializationException){
       return  Result.Error(NetworkError.SERIALIZATION)
   }catch (e:Exception){
       coroutineContext.ensureActive()
       return  Result.Error(NetworkError.UNKNOWN)

   }

    return responseToResult(response)
}