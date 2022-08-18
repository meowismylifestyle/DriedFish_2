package com.example.myapplication;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Fish_Item implements Serializable {
    private  int image;
    private String name;
    private String price;
    private String classLabel;

    public static final ArrayList<Fish_Item> allFishes = new ArrayList<>( Arrays.asList(
            new Fish_Item(R.drawable.bong, "Khô cá Bống","270.000/kg","bong"),
            new Fish_Item(R.drawable.chach, "Khô cá Chạch","460.000/kg","chach"),
            new Fish_Item(R.drawable.chivang, "Khô cá Chỉ Vàng","210.000/kg","chivang"),
            new Fish_Item(R.drawable.chot, "Khô cá Chốt","270.000/kg","chot"),
            new Fish_Item(R.drawable.com, "Khô cá Cơm","180.000/kg","com"),
            new Fish_Item(R.drawable.det, "Khô cá Đét","230.000/kg","det"),
            new Fish_Item(R.drawable.dong, "Khô cá Đỏng","180.000/kg","dong"),
            new Fish_Item(R.drawable.du, "Khô cá Đù","160.000/kg","du"),
            new Fish_Item(R.drawable.dua, "Khô cá Dứa","180.000/kg","dua"),
            new Fish_Item(R.drawable.duoi, "Khô cá Đuối","270.000/kg","duoi"),
            new Fish_Item(R.drawable.ho, "Khô cá Hố","200.000/kg","ho"),
            new Fish_Item(R.drawable.keo, "Khô cá Kèo","300.000/kg","keo"),
            new Fish_Item(R.drawable.khoai, "Khô cá Khoai","380.000/kg","khoai"),
            new Fish_Item(R.drawable.loc, "Khô cá Lóc","240.000/kg","loc"),
            new Fish_Item(R.drawable.longtong, "Khô cá Lòng Tong","440.000/kg","longtong"),
            new Fish_Item(R.drawable.luoitrau, "Khô cá Lưỡi Trâu","350.000/kg","luoitrau"),
            new Fish_Item(R.drawable.mai, "Khô cá Mai","370.000/kg","mai"),
            new Fish_Item(R.drawable.moi, "Khô cá Mối","240.000/kg","moi"),
            new Fish_Item(R.drawable.muc, "Khô Mực","900.000/kg","muc"),
            new Fish_Item(R.drawable.nhai, "Khô cá Nhái","500.000/kg","nhai"),
            new Fish_Item(R.drawable.nhong, "Khô cá Nhồng","200.000/kg","nhong"),
            new Fish_Item(R.drawable.nuc, "Khô cá Nục","85.000/kg","nuc"),
            new Fish_Item(R.drawable.ruoc,"Khô cá Ruốc","79.000/kg","ruoc"),
            new Fish_Item(R.drawable.sac, "Khô cá Sặc","270.000/kg","sac"),
            new Fish_Item(R.drawable.thieu, "Khô cá Thiều","85.000 ~ 650.000/kg","thieu"),
            new Fish_Item(R.drawable.thoiloi, "Khô cá Thòi Lòi","530.000/kg","thoiloi"),
            new Fish_Item(R.drawable.thu, "Khô cá Thu","270/kg","thu"),
            new Fish_Item(R.drawable.tom_80, "Khô Tôm","1.350.000/kg","tom"),
            new Fish_Item(R.drawable.tren_60, "Khô cá Trèn","500.000/kg","tren"),
            new Fish_Item(R.drawable.trich, "Khô cá Trích","300.000/kg","trich")
    ));

    public Fish_Item() {
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getClassLabel() { return this.classLabel; }

    @Override
    public String toString() {
        return "Fish_Item{" +
                "image=" + image +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", classLabel='" + classLabel + '\'' +
                '}';
    }

    public Fish_Item(int image, String name, String price, String classLabel) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.classLabel = classLabel;
    }

    public boolean isLabel(String label) {
        return this.classLabel.equals(label);
    }

    public static String[] getClassLabelList(ArrayList<Fish_Item> fishItems) {
        ArrayList<String> labels = new ArrayList<>();
        for (Fish_Item fish : fishItems) {
            String label = fish.getClassLabel();
            if (!labels.contains(label)) {
                labels.add(label);
            }
        }
        String[] result = new String[labels.size()];
        result = labels.toArray(result);
        return result;
    }

    public boolean isFavourite() {
        if (LoginActivity.currentUser == null)
            return false;
        return LoginActivity.currentUser.getFavouriteFishes().contains(this.classLabel);
    }

    public static Fish_Item getFishByLabel(String label) {
        for (Fish_Item fish : allFishes) {
            if (fish.classLabel.equals(label))
                return fish;
        }
        return null;
    }
}
