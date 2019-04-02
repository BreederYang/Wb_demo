package com.mybaitspuls.demo.entity;

/**
 * 动态
 */
public class Dynamic {
    private Integer id;
    private Integer Uid;
    private String Uname;
    private String context;
    private String imageId;
    private String createTime;
    private Integer likeCount;

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

    public String getUname() {
        return Uname;
    }

    public void setUname(String uname) {
        Uname = uname;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
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

    @Override
    public String toString() {
        return "Dynamic{" +
                "id=" + id +
                ", Uid=" + Uid +
                ", Uname=" + Uname +
                ", context='" + context + '\'' +
                ", imageId='" + imageId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", likeCount=" + likeCount +
                '}';
    }
}
