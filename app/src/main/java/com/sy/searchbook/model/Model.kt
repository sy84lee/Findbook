//package com.sy.searchbook.model
//
//import android.content.Context
//import android.util.Log
//import android.widget.Toast
//import io.reactivex.android.schedulers.AndroidSchedulers
//import io.reactivex.schedulers.Schedulers
//import retrofit2.HttpException
//import java.util.*
//
//class Model: Observable() {
//
//    init {
//
//    }
//
//    var wrapperRestAPI: WrapperRestAPI = WrapperRestAPI()
//    var bookList = ArrayList<BookInfo>()
//        get() = field
//
//    var bookIterator: Iterator<doc>? = null
//    var pageIndex: Int = 1
//
//    fun search(query: String) {
//        Log.i("TEST: ", "SungYong")
//        wrapperRestAPI.restAPI.getSearchBooks(query, "accuracy", pageIndex, 50)
//            .subscribeOn(Schedulers.io())
//            .map { t -> if (t.isSuccessful) t else throw HttpException(t) }
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { result ->
//                    if (result.isSuccessful) {
//                        val res: ResultGetSearchBooks = result.body()!!
//
//                        bookIterator = res.documents.iterator()
//
//                        while (bookIterator!!.hasNext()) {
//
//                            var bookInfo = bookIterator!!.next()
//                            bookList.add(BookInfo(null,
//                                bookInfo.title.toString(),
//                                bookInfo.contents.toString(),
//                                bookInfo.url.toString(),
//                                bookInfo.isbn.toString(),
//                                bookInfo.datetime.toString(),
//                                bookInfo.authors,
//                                bookInfo.publisher.toString(),
//                                bookInfo.translators,
//                                bookInfo.price,
//                                bookInfo.sale_price,
//                                bookInfo.thumbnail.toString(),
//                                bookInfo.status.toString(),
//                                0,
//                                false))
//                        }
//
//                        if (res.meta.is_end == false)
//                            pageIndex++
//
//                        setChanged()
//                        notifyObservers()
//
//                        Log.i("TEST: ", "success " + result.code())
//                    } else {
//                        Log.i("TEST: ", "failed " + result.code())
//                    }
//                },
//                { error ->
//                    if(error is HttpException) {
//                        Log.i("TEST Error: ", "${error.code()} exception.response.code : ${error.response()?.code()}")
//                    }
//                }
//            )
//    }
//
//}