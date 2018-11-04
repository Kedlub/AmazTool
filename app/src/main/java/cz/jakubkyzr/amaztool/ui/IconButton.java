package cz.jakubkyzr.amaztool.ui;

import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by Kubik on 02.11.2018.
 */

public class IconButton extends BaseButton {

    //Setting with a click listener, two strings and an icon

    View.OnClickListener onClickListener;
    String title;
    String subtitle;
    Drawable icon;

    public IconButton(Drawable icon, String title, String subtitle, View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.title = title;
        this.subtitle = subtitle;
        this.icon = icon;
    }
}