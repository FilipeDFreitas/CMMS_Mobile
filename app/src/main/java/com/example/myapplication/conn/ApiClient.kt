package com.example.myapplication.conn

import com.example.myapplication.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiClient {
    fun auth(user : String, pass: String, connResponse: ConnResponse<Token>){
        val call = RetrofitInitializer().connService().login(Login(user,pass))
        call.enqueue(object: Callback<Token?> {
            override fun onResponse(call: Call<Token?>?, response: Response<Token?>?) {
                if (response != null) {
                    if(response.isSuccessful){
                        response?.body()?.let {
                            val notes: Token = it
                            connResponse.success(notes)
                        }
                    }
                    else connResponse.fail()
                }
                else connResponse.fail()
            }

            override fun onFailure(call: Call<Token?>?, t: Throwable) {
                connResponse.error(t.message.toString())

            }
        })

    }
    // USER
    fun getUserList(token: String, connResponse: ConnResponse<TipoAPI>){
        val call = RetrofitInitializer().connService().getUserList("Bearer $token")
        call.enqueue(object: Callback<TipoAPI> {
            override fun onResponse(call: Call<TipoAPI>, response: Response<TipoAPI>) {
                if (response != null) {
                    if(response.isSuccessful){
                        response?.body()?.let {
                            connResponse.success(it)
                        }
                    }
                    else connResponse.fail()
                }
                else connResponse.fail()
            }

            override fun onFailure(call: Call<TipoAPI>, t: Throwable) {
                connResponse.error(t.message.toString())

            }
        })
    }
    fun getUser(token: String,userID: Int,connResponse: ConnResponse<User>){
        val call = RetrofitInitializer().connService().getUser("Bearer $token",userID)
        call.enqueue(object: Callback<UserAPI?> {
            override fun onResponse(call: Call<UserAPI?>?, response: Response<UserAPI?>?) {
                if (response != null) {
                    if(response.isSuccessful){
                        response?.body()?.let {
                            val notes: UserAPI = it
                            connResponse.success(notes.entity)
                        }
                    }
                    else connResponse.fail()
                }
                else connResponse.fail()
            }

            override fun onFailure(call: Call<UserAPI?>?, t: Throwable) {
                connResponse.error(t.message.toString())

            }
        })
    }
    fun putUser(token: String, tipo: User, connResponse: ConnResponse<UserAPI>){
        val call = RetrofitInitializer().connService().putUser("Bearer $token",tipo)
        call.enqueue(object: Callback<UserAPI> {
            override fun onResponse(call: Call<UserAPI>, response: Response<UserAPI>) {
                if (response != null) {
                    if(response.isSuccessful){
                        response?.body()?.let {
                            connResponse.success(it)
                        }
                    }
                    else connResponse.fail()
                }
                else connResponse.fail()
            }

            override fun onFailure(call: Call<UserAPI>, t: Throwable) {
                connResponse.error(t.message.toString())

            }
        })
    }
    fun postUser(token: String, tipo: User, connResponse: ConnResponse<UserAPI>){
        val call = RetrofitInitializer().connService().postUser("Bearer $token",tipo)
        call.enqueue(object: Callback<UserAPI> {
            override fun onResponse(call: Call<UserAPI>, response: Response<UserAPI>) {
                if (response != null) {
                    if(response.isSuccessful){
                        response?.body()?.let {
                            connResponse.success(it)
                        }
                    }
                    else connResponse.fail()
                }
                else connResponse.fail()
            }

            override fun onFailure(call: Call<UserAPI>, t: Throwable) {
                connResponse.error(t.message.toString())

            }
        })
    }
    fun deleteUser(token: String,userID: Int,connResponse: ConnResponse<TipoDeleteAPI>){
        val call = RetrofitInitializer().connService().deleteUser("Bearer $token",userID)
        call.enqueue(object: Callback<TipoDeleteAPI?> {
            override fun onResponse(call: Call<TipoDeleteAPI?>?, response: Response<TipoDeleteAPI?>?) {
                if (response != null) {
                    if(response.isSuccessful){
                        response?.body()?.let {
                            connResponse.success(it)
                        }
                    }
                    else connResponse.fail()
                }
                else connResponse.fail()
            }

            override fun onFailure(call: Call<TipoDeleteAPI?>?, t: Throwable) {
                connResponse.error(t.message.toString())

            }
        })
    }

    // TIPO
    fun getTipo(token: String, connResponse: ConnResponse<TipoAPI>){
        val call = RetrofitInitializer().connService().getTipo("Bearer $token")
        call.enqueue(object: Callback<TipoAPI> {
            override fun onResponse(call: Call<TipoAPI>, response: Response<TipoAPI>) {
                if (response != null) {
                    if(response.isSuccessful){
                        response?.body()?.let {
                            connResponse.success(it)
                        }
                    }
                    else connResponse.fail()
                }
                else connResponse.fail()
            }

            override fun onFailure(call: Call<TipoAPI>, t: Throwable) {
                connResponse.error(t.message.toString())

            }
        })
    }
    fun putTipo(token: String, tipo: Tipo, connResponse: ConnResponse<TipoAPI>){
        val call = RetrofitInitializer().connService().putTipo("Bearer $token",tipo)
        call.enqueue(object: Callback<TipoAPI> {
            override fun onResponse(call: Call<TipoAPI>, response: Response<TipoAPI>) {
                if (response != null) {
                    if(response.isSuccessful){
                        response?.body()?.let {
                            connResponse.success(it)
                        }
                    }
                    else connResponse.fail()
                }
                else connResponse.fail()
            }

            override fun onFailure(call: Call<TipoAPI>, t: Throwable) {
                connResponse.error(t.message.toString())

            }
        })
    }
    fun postTipo(token: String, tipo: Tipo, connResponse: ConnResponse<TipoAPI>){
        val call = RetrofitInitializer().connService().postTipo("Bearer $token",tipo)
        call.enqueue(object: Callback<TipoAPI> {
            override fun onResponse(call: Call<TipoAPI>, response: Response<TipoAPI>) {
                if (response != null) {
                    if(response.isSuccessful){
                        response?.body()?.let {
                            connResponse.success(it)
                        }
                    }
                    else connResponse.fail()
                }
                else connResponse.fail()
            }

            override fun onFailure(call: Call<TipoAPI>, t: Throwable) {
                connResponse.error(t.message.toString())

            }
        })
    }
    fun deleteTipo(token: String,userID: Int,connResponse: ConnResponse<TipoDeleteAPI>){
        val call = RetrofitInitializer().connService().deleteTipo("Bearer $token",userID)
        call.enqueue(object: Callback<TipoDeleteAPI?> {
            override fun onResponse(call: Call<TipoDeleteAPI?>?, response: Response<TipoDeleteAPI?>?) {
                if (response != null) {
                    if(response.isSuccessful){
                        response?.body()?.let {
                            connResponse.success(it)
                        }
                    }
                    else connResponse.fail()
                }
                else connResponse.fail()
            }

            override fun onFailure(call: Call<TipoDeleteAPI?>?, t: Throwable) {
                connResponse.error(t.message.toString())

            }
        })
    }

    //EQUIPAMENTOS

    fun getEquipList(token: String, connResponse: ConnResponse<ItemList>){
        val call = RetrofitInitializer().connService().getEquipList("Bearer $token")
        call.enqueue(object: Callback<ItemList> {
            override fun onResponse(call: Call<ItemList>, response: Response<ItemList>) {
                if (response != null) {
                    if(response.isSuccessful){
                        response?.body()?.let {
                            connResponse.success(it)
                        }
                    }
                    else connResponse.fail()
                }
                else connResponse.fail()
            }

            override fun onFailure(call: Call<ItemList>, t: Throwable) {
                connResponse.error(t.message.toString())

            }
        })
    }
    fun getEquip(token: String,userID: Int,connResponse: ConnResponse<ItemAPI>){
        val call = RetrofitInitializer().connService().getEquip("Bearer $token",userID)
        call.enqueue(object: Callback<ItemAPI?> {
            override fun onResponse(call: Call<ItemAPI?>?, response: Response<ItemAPI?>?) {
                if (response != null) {
                    if(response.isSuccessful){
                        response?.body()?.let {
                            connResponse.success(it)
                        }
                    }
                    else connResponse.fail()
                }
                else connResponse.fail()
            }

            override fun onFailure(call: Call<ItemAPI?>?, t: Throwable) {
                connResponse.error(t.message.toString())

            }
        })
    }
    fun putEquip(token: String, item: Item, connResponse: ConnResponse<ItemList>){
        val call = RetrofitInitializer().connService().putEquip("Bearer $token",item)
        call.enqueue(object: Callback<ItemList> {
            override fun onResponse(call: Call<ItemList>, response: Response<ItemList>) {
                if (response != null) {
                    if(response.isSuccessful){
                        response?.body()?.let {
                            connResponse.success(it)
                        }
                    }
                    else connResponse.fail()
                }
                else connResponse.fail()
            }

            override fun onFailure(call: Call<ItemList>, t: Throwable) {
                connResponse.error(t.message.toString())

            }
        })
    }
    fun postEquip(token: String, item: Item, connResponse: ConnResponse<ItemList>){
        val call = RetrofitInitializer().connService().postEquip("Bearer $token",item)
        call.enqueue(object: Callback<ItemList> {
            override fun onResponse(call: Call<ItemList>, response: Response<ItemList>) {
                if (response != null) {
                    if(response.isSuccessful){
                        response?.body()?.let {
                            connResponse.success(it)
                        }
                    }
                    else connResponse.fail()
                }
                else connResponse.fail()
            }

            override fun onFailure(call: Call<ItemList>, t: Throwable) {
                connResponse.error(t.message.toString())

            }
        })
    }
    fun deleteEquip(token: String,userID: Int,connResponse: ConnResponse<ItemDeleteAPI>){
        val call = RetrofitInitializer().connService().deleteEquip("Bearer $token",userID)
        call.enqueue(object: Callback<ItemDeleteAPI?> {
            override fun onResponse(call: Call<ItemDeleteAPI?>?, response: Response<ItemDeleteAPI?>?) {
                if (response != null) {
                    if(response.isSuccessful){
                        response?.body()?.let {
                            connResponse.success(it)
                        }
                    }
                    else connResponse.fail()
                }
                else connResponse.fail()
            }

            override fun onFailure(call: Call<ItemDeleteAPI?>?, t: Throwable) {
                connResponse.error(t.message.toString())

            }
        })
    }

    // LOCAL
    fun getLocal(token: String, connResponse: ConnResponse<LocalAPI>){
        val call = RetrofitInitializer().connService().getLocal("Bearer $token")
        call.enqueue(object: Callback<LocalAPI> {
            override fun onResponse(call: Call<LocalAPI>, response: Response<LocalAPI>) {
                if (response != null) {
                    if(response.isSuccessful){
                        response?.body()?.let {
                            connResponse.success(it)
                        }
                    }
                    else connResponse.fail()
                }
                else connResponse.fail()
            }

            override fun onFailure(call: Call<LocalAPI>, t: Throwable) {
                connResponse.error(t.message.toString())

            }
        })
    }
    fun putLocal(token: String, tipo: Local, connResponse: ConnResponse<LocalAPI>){
        val call = RetrofitInitializer().connService().putLocal("Bearer $token",tipo)
        call.enqueue(object: Callback<LocalAPI> {
            override fun onResponse(call: Call<LocalAPI>, response: Response<LocalAPI>) {
                if (response != null) {
                    if(response.isSuccessful){
                        response?.body()?.let {
                            connResponse.success(it)
                        }
                    }
                    else connResponse.fail()
                }
                else connResponse.fail()
            }

            override fun onFailure(call: Call<LocalAPI>, t: Throwable) {
                connResponse.error(t.message.toString())

            }
        })
    }
    fun postLocal(token: String, tipo: Local, connResponse: ConnResponse<LocalAPI>){
        val call = RetrofitInitializer().connService().postLocal("Bearer $token",tipo)
        call.enqueue(object: Callback<LocalAPI> {
            override fun onResponse(call: Call<LocalAPI>, response: Response<LocalAPI>) {
                if (response != null) {
                    if(response.isSuccessful){
                        response?.body()?.let {
                            connResponse.success(it)
                        }
                    }
                    else connResponse.fail()
                }
                else connResponse.fail()
            }

            override fun onFailure(call: Call<LocalAPI>, t: Throwable) {
                connResponse.error(t.message.toString())

            }
        })
    }
    fun deleteLocal(token: String,userID: Int,connResponse: ConnResponse<LocalDeleteAPI>){
        val call = RetrofitInitializer().connService().deleteLocal("Bearer $token",userID)
        call.enqueue(object: Callback<LocalDeleteAPI?> {
            override fun onResponse(call: Call<LocalDeleteAPI?>?, response: Response<LocalDeleteAPI?>?) {
                if (response != null) {
                    if(response.isSuccessful){
                        response?.body()?.let {
                            connResponse.success(it)
                        }
                    }
                    else connResponse.fail()
                }
                else connResponse.fail()
            }

            override fun onFailure(call: Call<LocalDeleteAPI?>?, t: Throwable) {
                connResponse.error(t.message.toString())

            }
        })
    }
}