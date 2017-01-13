package net.lemonsoft.lemonkit.ui_kit.delegate.LKUITableView;

import android.view.View;

import net.lemonsoft.lemonkit.native_ui.extend.view.LKTableView;
import net.lemonsoft.lemonkit.native_ui.extend.view.LKUITableViewCell;
import net.lemonsoft.lemonkit.native_ui.model.LKIndexPath;


/**
 * 数据源接口
 */
public interface LKUITableViewDataSource {
    // required
    Integer numberOfRowsInSection(LKTableView tableView, Integer section);

    LKUITableViewCell cellForRowAtIndexPath(LKTableView tableView, LKIndexPath indexPath);

    // optional
    Integer numberOfSectionsInTableView(LKTableView tableView);

    String titleForHeaderInSection(LKTableView tableView, Integer section);

    String titleForFooterInSection(LKTableView tableView, Integer section);

    View viewForHeaderInSection(LKTableView tableView, Integer section);

    View viewForFooterInSection(LKTableView tableView, Integer section);


}