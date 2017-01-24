package net.lemonsoft.lemonkit.native_ui.extend.view;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TableLayout;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * LKUITableViewCell的行元素
 * Created by LiuRi on 16/8/3.
 */
public class LKTableViewCell extends RelativeLayout {

    /**
     * 服用标识
     */
    private String reuseIdentifier = null;

    public LKTableViewCell(Context context) {
        super(context);
        initCell();
    }

    public LKTableViewCell(Context context, String reuseIdentifier) {
        super(context);
        this.reuseIdentifier = reuseIdentifier;
        initCell();
    }

    public void initCell() {
        this.setBackgroundColor(Color.WHITE);
    }

    public String getReuseIdentifier() {
        return reuseIdentifier;
    }
}
