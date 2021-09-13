package com.example.news.menu;
import com.example.news.R;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;

public class BuilderManager{

    private static int[] imageResources = new int[]{
            R.drawable.homepage,
            R.drawable.createtask_fill,
            R.drawable.star,
            R.drawable.header,
            R.drawable.nation,
            R.drawable.eco,
            R.drawable.sport,
            R.drawable.war,
            R.drawable.yule
    };

    private static int imageResourceIndex = 0;

    public static int getImageResource() {
        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
        return imageResources[imageResourceIndex++];
    }

    public static SimpleCircleButton.Builder getSimpleCircleButtonBuilder() {
        return new SimpleCircleButton.Builder()
                .normalImageRes(getImageResource());
    }



    private static BuilderManager ourInstance = new BuilderManager();

    public static BuilderManager getInstance() {
        return ourInstance;
    }

    private BuilderManager() {
    }
}
