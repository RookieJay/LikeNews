package pers.ll.likenews.model;

public class XWValidateResult {

    /**
     * mid : 春风十里
     * title : null
     * album : null
     * author : null
     * url : https://api.mlwei.com/music/api/wy/?key=523077333&cache=&type=url&id=春风十里
     * pic : ?param=200x200
     * lrc : https://api.mlwei.com/music/api/wy/?key=523077333&cache=&type=lrc&id=春风十里
     */

    private String mid;
    private String title;
    private String album;
    private String author;
    private String url;
    private String pic;
    private String lrc;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getLrc() {
        return lrc;
    }

    public void setLrc(String lrc) {
        this.lrc = lrc;
    }
}
