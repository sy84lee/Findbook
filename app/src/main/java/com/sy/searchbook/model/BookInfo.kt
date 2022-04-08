package com.sy.searchbook.model

import android.graphics.drawable.Drawable
import java.io.Serializable

class BookInfo( val imgProfile:Drawable?,
                val title:String,
                val contents:String,
                val url:String,
                val isbn:String,
                val dateTime:String,
                val authors:List<String>,
                val publisher:String,
                val translators:List<String>,
                val price:Int,
                val sale_price:Int,
                val thumnail:String,
                val status:String,
                var position:Int,
                var like:Boolean) : Serializable {
}