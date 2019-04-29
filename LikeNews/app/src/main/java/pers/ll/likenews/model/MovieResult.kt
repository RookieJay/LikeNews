package pers.ll.likenews.model

data class MovieResult(
    val count: Int, //单页条数
    val start: Int, //数据的开始项
    val subjects: List<Subject>, //电影列表
    val title: String,  //电影名中文名
    val total: Int  //数据总条数
) {

    /**
     * 电影列表
     */
    data class Subject(
        val alt: String,
        val casts: List<Cast>, //主演列表
        val collect_count: Int,
        val directors: List<Director>, //导演列表
        val genres: List<String>, //电影类型
        val id: String,
        val images: Images,
        val original_title: String,
        val rating: Rating,
        val subtype: String,
        val title: String,
        val year: String
    ) {

        /**
         * 评分信息
         */
        data class Rating(
            val average: Double,
            val max: Int,
            val min: Int,
            val stars: String
        )


        /**
         * 电影图url
         */
        data class Images(
            val large: String,
            val medium: String,
            val small: String
        )

        /**
         * 导演列表
         */
        data class Director(
            val alt: String,
            val avatars: AvatarsX,
            val id: String,
            val name: String
        ) {
            data class AvatarsX(
                val large: String,
                val medium: String,
                val small: String
            )
        }

        /**
         * 主演列表
         */
        data class Cast(
            val alt: String,
            val avatars: Avatars,
            val id: String,
            val name: String    //主演名
        ) {
            /**
             * 电影评分
             */
            data class Avatars(
                val large: String,
                val medium: String,
                val small: String
            )
        }
    }

}