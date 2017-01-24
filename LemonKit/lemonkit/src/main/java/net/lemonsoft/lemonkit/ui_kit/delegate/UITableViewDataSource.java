package net.lemonsoft.lemonkit.ui_kit.delegate;

import android.view.View;

import net.lemonsoft.lemonkit.native_ui.extend.delegate.LKTableViewDataSource;
import net.lemonsoft.lemonkit.native_ui.extend.view.LKTableView;
import net.lemonsoft.lemonkit.native_ui.extend.view.LKTableViewCell;
import net.lemonsoft.lemonkit.native_ui.model.LKIndexPath;
import net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view.UITableView;
import net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view.UITableViewCell;

/**
 * Created by zhsu on 2017/1/13.
 */

public  interface UITableViewDataSource extends LKTableViewDataSource {

    Integer numberOfRowsInSection(UITableView tableView, Integer section);

    UITableViewCell cellForRowAtIndexPath(UITableView tableView, LKIndexPath indexPath);

    Integer numberOfSectionsInTableView(UITableView tableView);

    String titleForHeaderInSection(UITableView tableView, Integer section);

    String titleForFooterInSection(UITableView tableView, Integer section);

    View viewForHeaderInSection(UITableView tableView, Integer section);

    View viewForFooterInSection(UITableView tableView, Integer section);
}
