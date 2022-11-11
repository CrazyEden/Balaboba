package com.example.balaboba.data.model

data class BalabobaResponse (
    var badQuery: Int?,
    var query: String?,
    var text: String?,
    var error: Int?,
    var isCached: Int?,
    var intro: Int?,
    var signature: String?
)
