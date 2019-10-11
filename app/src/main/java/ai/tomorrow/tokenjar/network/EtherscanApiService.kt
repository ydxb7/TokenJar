package ai.tomorrow.tokenjar.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "http://api-ropsten.etherscan.io/"
//private const val BASE_URL = "https://mars.udacity.com/"

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object pointing to the desired URL
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface EtherscanApiService {

    @GET("api")
    fun getHistory(@Query("module") module: String,
                   @Query("action") action: String,
                   @Query("address") address: String,
                   @Query("startblock") startblock: Long,
                   @Query("endblock") endblock: Long,
                   @Query("page") page: Int,
                   @Query("offset") offset: Int,
                   @Query("sort") sort: String,
                   @Query("apikey") apikey: String
    ):
            Call<String>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object EtherscanApi {
    val retrofitService : EtherscanApiService by lazy { retrofit.create(EtherscanApiService::class.java) }
}