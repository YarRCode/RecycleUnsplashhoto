package com.testrpackage.recycleviewtest;

public class RecyckeModel {

    String nameImange;
    String urlOfImage;
    String authorName;
    Double resol;

    public RecyckeModel(String nameImange, String urlOfImage, String authorName, double resol) {
        this.nameImange = nameImange;
        this.urlOfImage = urlOfImage;
        this.authorName = authorName;
        this.resol = resol;
    }

    public String getNameImange() {
        return nameImange;
    }

    public String getUrlOfImage() {
        return urlOfImage;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Double getResol() {
        return resol;
    }
}
