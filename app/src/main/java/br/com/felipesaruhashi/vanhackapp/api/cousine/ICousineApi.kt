package br.com.felipesaruhashi.vanhackapp.api.cousine

import br.com.felipesaruhashi.vanhackapp.models.Cousine
import br.com.felipesaruhashi.vanhackapp.models.Store
import retrofit2.http.Path
import rx.Observable


interface ICousineApi {


    fun getCousines():Observable<List<Cousine>>

    fun getCousinesStores(@Path("cousineId") cousineId:Int) :Observable<List<Store>>


    fun queryCousines(@Path("searchText") searchText:String) :Observable<List<Cousine>>


}
