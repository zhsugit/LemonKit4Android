package net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view;

import android.content.Context;
import android.graphics.Color;

import net.lemonsoft.lemonkit.native_ui.extend.view.LKTableView;
import net.lemonsoft.lemonkit.native_ui.extend.view.LKTableViewCell;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by zhsu on 2017/1/13.
 */


//public class UITableViewCell<T extends LKTableViewCell> extends UIView<T> {

public class UITableViewCell extends UIView<LKTableViewCell> {
    String reuseIdentifier;

    public UITableViewCell() {
        super(LKTableViewCell.class);

    }

    public UITableViewCell(String reuseIdentifier) {
        super(LKTableViewCell.class);
        this.reuseIdentifier = reuseIdentifier;
        initCell();
    }

    public void initCell() {
        this.setBackgroundColor(Color.WHITE);
    }

    public String getReuseIdentifier() {
        return ((LKTableViewCell) _rView).getReuseIdentifier();

    }

}
