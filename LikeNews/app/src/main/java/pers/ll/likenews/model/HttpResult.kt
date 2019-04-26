package pers.ll.likenews.model

class HttpResult<T> {
    var result: String? = null
    var code: Int = 0
    var data: List<T>? = null
}
