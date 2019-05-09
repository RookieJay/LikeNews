package pers.ll.likenews.model;

public enum NewsType {

    SOCIAL("社会"), ENTERTAINMENT("娱乐"), TECH("科技"), CAR("汽车"), SPORT("运动"), FINANCE("金融"), MILITARY("军事"), QA("问答"), HOT("热点");

    private String type;

    NewsType(String status) {
        this.type = status;
    }

    public static NewsType parse(String param) {
        switch (param) {
            case "0":
                return SOCIAL;
            case "1":
                return ENTERTAINMENT;
            case "2":
                return TECH;
            case "3":
                return CAR;
            case "4":
                return SPORT;
            case "5":
                return FINANCE;
            case "6":
                return MILITARY;
            default:
                return HOT;
        }
    }

    @Override
    public String toString() {
        return type;
    }
}
