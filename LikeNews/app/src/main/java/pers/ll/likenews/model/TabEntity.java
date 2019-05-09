package pers.ll.likenews.model;

public class TabEntity {

    private String id;
    private String title;
    private String type;

    public TabEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return NewsType.parse(type).toString();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public NewsType getTypeEnum() {
        return NewsType.parse(type);
    }

    public void setType(String type) {
        this.type = type;
    }

}
