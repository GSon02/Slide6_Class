package com.example.slide6_class;

public class mapModel {
    private int ID;
    private String nameStore;
    private String kinhDo;
    private String viDo;

    public mapModel() {
    }

    public mapModel(int ID, String nameStore, String kinhDo, String viDo) {
        this.ID = ID;
        this.nameStore = nameStore;
        this.kinhDo = kinhDo;
        this.viDo = viDo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public String getKinhDo() {
        return kinhDo;
    }

    public void setKinhDo(String kinhDo) {
        this.kinhDo = kinhDo;
    }

    public String getViDo() {
        return viDo;
    }

    public void setViDo(String viDo) {
        this.viDo = viDo;
    }
}
