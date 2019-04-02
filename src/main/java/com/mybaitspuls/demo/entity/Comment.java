package com.mybaitspuls.demo.entity;
/*
 * 评论讨论
 */
public class Comment {
    private Integer id;
    private Integer Uid;
    private Integer Did;
    private String uName;
    private String context;
    private String createTime;
    private Integer likeCount;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", Uid=" + Uid +
                ", Did=" + Did +
                ", uName=" + uName +
                ", context='" + context + '\'' +
                ", createTime='" + createTime + '\'' +
                ", likeCount=" + likeCount +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return Uid;
    }

    public void setUid(Integer uid) {
        Uid = uid;
    }

    public Integer getDid() {
        return Did;
    }

    public void setDid(Integer did) {
        Did = did;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }
}
