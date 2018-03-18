package br.com.felipesaruhashi.vanhackapp.api.retrofit

import br.com.felipesaruhashi.vanhackapp.VanhackApp
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.schedulers.Schedulers


class VanhackApi : IApi {

    companion object {
        val URL = "http://api-vanhack-event-sp.azurewebsites.net/"
    }

    override fun generateRetrofit(): Observable<Retrofit> {
        return Observable.create { subscriber ->

            var builder = OkHttpClient.Builder()


            builder.addInterceptor { chain ->

                val original: Request = chain.request()

                val requestBuilder = original.newBuilder()

                if ( VanhackApp.token != null && (VanhackApp.token as String).isNotEmpty()) {
                    requestBuilder.header("Authorization", VanhackApp.token )
                }

                val request: Request = requestBuilder.build()
                chain.proceed(request)
            }

            var okHttpClient: OkHttpClient = builder.build()

            val gson = GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create()


            var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Companion.URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

            subscriber.onNext(retrofit)
            subscriber.onCompleted()

        }
    }
}



