package com.example.balaboba.di

import com.example.balaboba.data.local.room.AppDatabase
import com.example.balaboba.data.local.room.BalabobEntity
import com.example.balaboba.data.model.Balabob
import com.example.balaboba.data.network.ApiInterface
import com.example.balaboba.data.network.model.BalabobaRequest
import com.example.balaboba.data.network.model.BalabobaRespone
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val network:ApiInterface,
    private val db:AppDatabase
) {
    suspend fun getAllSavedData():List<Balabob> = db.getDao().getAllBalabobs()
    suspend fun insertInDb(balabobEntity: BalabobEntity){
        db.getDao().insertBalabob(balabobEntity)
    }
    suspend fun load(balabobaRequest: BalabobaRequest):Response<BalabobaRespone>
        = network.loadResponse(balabobaRequest)

}