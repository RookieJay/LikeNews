package pers.ll.likenews.model;

public class Poetry {

    /**
     * title : 帝京篇十首 一
     * content : 秦川雄帝宅，函谷壮皇居。|绮殿千寻起，离宫百雉余。|连甍遥接汉，飞观迥凌虚。|云日隐层阙，风烟出绮疏。
     * authors : 太宗皇帝
     */
    private String title;
    private String content;
    private String authors;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }
}
