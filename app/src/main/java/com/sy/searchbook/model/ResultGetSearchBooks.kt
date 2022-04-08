package com.sy.searchbook.model

import com.google.gson.annotations.SerializedName

//Response from server
data class ResultGetSearchBooks(
    @field:SerializedName("meta") val meta: metadata = metadata(0, 0, false),
    @field:SerializedName("documents") val documents: List<doc> = emptyList()
)

data class metadata (
    var total_count: Int = 0,
    var pageable_count: Int = 0,
    var is_end: Boolean = false
)

data class doc (
    var title: String = "",
    var contents: String = "",
    var url: String = "",
    var isbn: String = "",
    var datetime: String = "",
    var authors: List<String>,
    var publisher: String = "",
    var translators: List<String>,
    var price: Int = 0,
    var sale_price: Int = 0,
    var thumbnail: String = "",
    var status: String = ""
)