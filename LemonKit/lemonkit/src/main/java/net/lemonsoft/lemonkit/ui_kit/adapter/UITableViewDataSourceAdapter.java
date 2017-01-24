package net.lemonsoft.lemonkit.ui_kit.adapter;

import android.view.View;

import net.lemonsoft.lemonkit.native_ui.model.LKIndexPath;
import net.lemonsoft.lemonkit.ui_kit.delegate.UITableViewDataSource;
import net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view.UITableView;
import net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view.UITableViewCell;

/**
 * Created by zhsu on 2017/1/13.
 */

public abstract class UITableViewDataSourceAdapter implements UITableViewDataSource {

    @Override
    public Integer numberOfRowsInSection(UITableView tableView, Integer section) {
        return null;
    }

    @Override
    public UITableViewCell cellForRowAtIndexPath(UITableView tableView, LKIndexPath indexPath) {
        return null;
    }

    @Override
    public Integer numberOfSectionsInTableView(UITableView tableView) {
        return null;
    }

    @Override
    public String titleForHeaderInSection(UITableView tableView, Integer section) {
        return null;
    }

    @Override
    public String titleForFooterInSection(UITableView tableView, Integer section) {
        return null;
    }

    @Override
    public View viewForHeaderInSection(UITableView tableView, Integer section) {
        return null;
    }

    @Override
    public View viewForFooterInSection(UITableView tableView, Integer section) {
        return null;
    }
}
