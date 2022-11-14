package com.example.balaboba.data.model

import com.google.gson.annotations.SerializedName

data class BalabobaResponse(
    @SerializedName("bad_query")
    var badQuery: Int,
    var query: String?,
    var text: String,
    var error: Int?,
    var isCached: Int?,
    var intro: Int?,
    var signature: String?
)
