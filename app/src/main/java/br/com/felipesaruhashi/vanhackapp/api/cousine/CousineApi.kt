package br.com.felipesaruhashi.vanhackapp.api.cousine

import br.com.felipesaruhashi.vanhackapp.VanhackApp
import br.com.felipesaruhashi.vanhackapp.api.retrofit.IApi
import br.com.felipesaruhashi.vanhackapp.api.retrofit.VanhackApi
import br.com.felipesaruhashi.vanhackapp.models.Cousine
import br.com.felipesaruhashi.vanhackapp.models.Store
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable
import javax.inject.Inject

class CousineApi:  ICousineApi {


//    @Inject lateinit var api: IApi

    var api = VanhackApi()

    init {
        VanhackApp.component.inject(this)
    }

    override fun getCousines(): Observable<List<Cousine>> {
        return api.generateRetrofit()
        .flatMap {
            it.create(CousineService::class.java).getCousines()
        }
    }

    override fun getCousinesStores(cousineId: Int): Observable<List<Store>> {
        return api.generateRetrofit()
        .flatMap {
            it.create(CousineService::class.java).getCousinesStores(cousineId)
        }
    }

    override fun queryCousines(searchText: String): Observable<List<Cousine>> {
        return api.generateRetrofit()
        .flatMap {
            it.create(CousineService::class.java).queryCousines(searchText)
        }
    }
}

interface CousineService {

    @GET("api/v1/Cousine")
    fun getCousines():Observable<List<Cousine>>

    @GET("api/v1/Cousine/{cousineId}/stores")
    fun getCousinesStores(@Path("cousineId") cousineId:Int) :Observable<List<Store>>

    @GET("api/v1/Cousine/search/{searchText}")
    fun queryCousines(@Path("searchText") searchText:String) :Observable<List<Cousine>>

}