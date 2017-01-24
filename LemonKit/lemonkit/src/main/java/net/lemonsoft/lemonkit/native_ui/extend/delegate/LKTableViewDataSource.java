package net.lemonsoft.lemonkit.native_ui.extend.delegate;

import android.view.View;

import net.lemonsoft.lemonkit.native_ui.extend.view.LKTableView;
import net.lemonsoft.lemonkit.native_ui.extend.view.LKTableViewCell;
import net.lemonsoft.lemonkit.native_ui.model.LKIndexPath;


/**
 * 数据源接口
 */
public interface LKTableViewDataSource {
    // required
    Integer numberOfRowsInSection(LKTableView tableView, Integer section);

    LKTableViewCell cellForRowAtIndexPath(LKTableView tableView, LKIndexPath indexPath);

    // optional
    Integer numberOfSectionsInTableView(LKTableView tableView);

    String titleForHeaderInSection(LKTableView tableView, Integer section);

    String titleForFooterInSection(LKTableView tableView, Integer section);

    View viewForHeaderInSection(LKTableView tableView, Integer section);

    View viewForFooterInSection(LKTableView tableView, Integer section);


}