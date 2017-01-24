package net.lemonsoft.lemonkit.ui_kit.delegate;

import net.lemonsoft.lemonkit.native_ui.model.LKIndexPath;
import net.lemonsoft.lemonkit.native_ui.model.LKTableViewRowAction;
import net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view.UITableView;

import java.util.List;

/**
 * UITableView 代理方法
 * Created by zhsu on 2017/1/13.
 */

public interface UITableViewDelegate {

    Integer heightForRowAtIndexPath(UITableView tableView, LKIndexPath indexPath);

    Integer heightForHeaderInSection(UITableView tableView, Integer section);

    Integer heightForFooterInSection(UITableView tableView, Integer section);

    void didSelectRowAtIndexPath(UITableView tableView, LKIndexPath indexPath);

    List<LKTableViewRowAction> editActionsForRowAtIndexPath(UITableView tableView, LKIndexPath indexPath);
}
