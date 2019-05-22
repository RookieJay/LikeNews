package pers.ll.likenews.model

class MusicResult<T>{
    var result: String? = null
    var timestamp : Long = 0L
    var code: Int = 0
    var data: List<T>? = null
}
