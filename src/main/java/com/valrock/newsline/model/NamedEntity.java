package com.valrock.newsline.model;

/**
 * Created by Валерий on 17.03.2017.
 */
public class NamedEntity extends BaseEntity {
    protected String name;

    public NamedEntity(){

    }

    protected NamedEntity(Integer id, String name){
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
