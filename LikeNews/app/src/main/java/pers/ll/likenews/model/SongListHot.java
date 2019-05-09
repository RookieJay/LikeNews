package pers.ll.likenews.model;

public  class SongListHot {
        /**
         * id : 2765820785
         * title : 告白吧！喜欢和你有关的一切
         * creator : 螚安Vivienne
         * description : 祝你喜欢的人恰巧也喜欢你
         祝你每次考试都能有进步
         祝你常在换季的衣服里发现花剩的零钱
         祝你在下雨天时路上的空车都不拒载
         祝你新尝试的美食总比想象中的更好吃
         祝你做过的美梦都不会忘记
         祝你的心情永远像周五的下午
         祝你永远活的像个孩子
         祝你告白成功
         * coverImgUrl : http://p1.music.126.net/lkwpfzUpj0_3QB-Tyrf2bA==/109951164016585910.jpg?param=400y400
         * songNum : 103
         * playCount : 12367
         */

        private long id;
        private String title;
        private String creator;
        private String description;
        private String coverImgUrl;
        private int songNum;
        private int playCount;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCoverImgUrl() {
            return coverImgUrl;
        }

        public void setCoverImgUrl(String coverImgUrl) {
            this.coverImgUrl = coverImgUrl;
        }

        public int getSongNum() {
            return songNum;
        }

        public void setSongNum(int songNum) {
            this.songNum = songNum;
        }

        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
        }
}
