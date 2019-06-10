package pers.ll.likenews.model;

public  class DailyArticle {

    /**
     * date : {"curr":"20110711","prev":"20110710","next":"20110712"}
     * author : 王文华
     * title : 有空小姐和篮板球小姐
     * digest : 我们常问别人“你过得好不好”，但很少能从答案中得出明确的结论。我的第一位女朋友让我学到一个简单的判断标准：看一个人过得好不好，就看他有没有空
     * content : <p>我们常问别人“你过得好不好”，但很少能从答案中得出明确的结论。</p><p>我的第一位女朋友让我学到一个简单的判断标准：看一个人过得好不好，就看他有没有空。</p><p>如果你约他时他总是没空，而且是真的没空，那他过得不会太好。如果他永远随传随到，那他的快乐指数应该很高。</p><p>越快乐的人，越好约。</p><p>“有空小姐”永远有空。每次约她，不管是一周前或一小时前；不管用电话或短信，她总是立刻回复，“没问题”“好极了”“约几点”。她不像我要斟酌两小时才回短信，回时还要探听：“还有谁”“约在哪”“吃什么”。如果人、时间或食物不对，友情也跟着倒霉。</p><p>你也许会觉得“有空小姐”很闲，但她是日理万机的总经理。更厉害的是，她无役不与，对任何朋友都来者不拒。所以当我临时约她，她若已经跟别人约好了，她会邀我加入。也就是说，当我想见到她时，她永远会让我见到。</p><p>“有空小姐”让我看到“过得好”的特征。她在任何时空、情境、团体中，都能做自己。于是她可以跟任何人吃饭，没有太多的原则和禁忌。你约她打麻将，她会来。她不会打，但乐意在客厅玩Wii。</p><p>过得好的人常流动。她漫游，但不至于奔波。她可以在任何时空做该做的事，没有赶不及或走不开的道理。</p><p>过得好的人有弹性。自己或朋友，都是可以调整或融合的人。于是她可以做任何排列组合，不用把时间过度分割。你约她吃素食，她已经在吃火锅，于是她邀你去吃素食火锅，而你也乐意接受。</p><p>过得好的人，永远都能接听手机。我过得不好，常有未接电话，但真正未接到的，可能是我的心。我常以为忙碌与成就成正比，快乐的人一定要攻城略地。但“有空小姐”让我看到：只要能收复时间的失地，那就是最大的幸福。</p><p>“篮板球小姐”不会打篮球，但她会抓篮板球。事实上，她本身就是一个篮板球。</p><p>她喜欢游泳，但游得不好。她总是把标准池当做浴缸来泡。她虽然不是选手，有一个动作却很专业，就是游到尽头时，反转踢墙，朝反方向游回去。她的反转之所以漂亮，是因为她既不会溅起水花，也不会发出声响。</p><p>我说她是篮板球，是因为她总能从投不进的挫败中弹起。我喜欢她游泳的反转，因为她不会把反弹搞得可歌可泣。</p><p>“篮板球小姐”吃过一些苦头，虽然你看不出来。她最亲密的家人，在她小时候过世；她做生意时曾经风光，如今电话很少冉响。</p><p>对于过去，你问，她不逃避，你不问，她也不会提起。仿佛人生的不如意就像电视剧的情节，看时难过得要死，看完后可以立刻转头骂不去洗澡的孩子。</p><p>今不如昔的人通常邋遢．但她总是穿Prada;有创伤的人通常难搞，但她柔软得像蛋糕。她从不因为自己委屈，就对别人耍小姐脾气。</p><p>她从不因为人生亏欠她很多，就出门要求别人埋单。每次出去玩，她总是开着她的宝马，把每个人送回家。她走在街上从不带包包，我在她身上看到没有包袱的美好。</p><p>大部分人球不进篮，就滚到界外；游到池边，就停下来休息。篮板球小姐不这样。她总是一直留在界内，缓慢却稳定地前进。阻力大，不会让她不敢下水，顶多游得慢一些。</p><p>人生路，我们都带着包袱。明明没有人对我们开火，我们还匍匐前进。风吹草动，都让我们杯弓蛇影。包袱是好的，它让我们不再犯同样的错误。但也是坏的，它让我们看不到新的可能。</p><p>包袱自然带来成见，所以我们不吃这个东西，不和那个人打交道。活得越久，禁忌越多。</p><p>“篮板球小姐”把人生和自己都看得很轻，所以千斤的包袱也都变成空气。她没有禁忌，不管到哪家餐厅，东西都能吃完。她没有远景、梦想或生涯规划。她活着的目的，似乎只是把每个人送回家。</p><p>我认识很多成功人士，他们都是漂亮的空心球。他们的脸从不曾重重摔在地板上，没方向地弹来弹去。我的好朋友并不成功，但我觉得她的球路更有看头。</p>
     * wc : 1462
     */

    private Date date;
    private String author;
    private String title;
    private String digest;
    private String content;
    private int wc;

    public Date getDate() { return date;}

    public void setDate(Date date) { this.date = date;}

    public String getAuthor() { return author;}

    public void setAuthor(String author) { this.author = author;}

    public String getTitle() { return title;}

    public void setTitle(String title) { this.title = title;}

    public String getDigest() { return digest;}

    public void setDigest(String digest) { this.digest = digest;}

    public String getContent() { return content;}

    public void setContent(String content) { this.content = content;}

    public int getWc() { return wc;}

    public void setWc(int wc) { this.wc = wc;}

    public static class Date {

        /**
         * curr : 20110711
         * prev : 20110710
         * next : 20110712
         */

        private String curr;
        private String prev;
        private String next;

        public String getCurr() { return curr;}

        public void setCurr(String curr) { this.curr = curr;}

        public String getPrev() { return prev;}

        public void setPrev(String prev) { this.prev = prev;}

        public String getNext() { return next;}

        public void setNext(String next) { this.next = next;}
    }
}
