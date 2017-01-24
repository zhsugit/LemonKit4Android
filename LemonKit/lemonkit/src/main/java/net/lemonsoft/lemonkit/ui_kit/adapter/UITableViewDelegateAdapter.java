package net.lemonsoft.lemonkit.ui_kit.adapter;

import net.lemonsoft.lemonkit.native_ui.model.LKIndexPath;
import net.lemonsoft.lemonkit.native_ui.model.LKTableViewRowAction;
import net.lemonsoft.lemonkit.ui_kit.delegate.UITableViewDelegate;
import net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view.UITableView;

import java.util.List;

/**
 * UITableView 代理函数 适配器
 * Created by zhsu on 2017/1/13.
 */

public abstract class UITableViewDelegateAdapter implements UITableViewDelegate {

    @Override
    public Integer heightForRowAtIndexPath(UITableView tableView, LKIndexPath indexPath) {
        return null;
    }

    @Override
    public Integer heightForHeaderInSection(UITableView tableView, Integer section) {
        return null;
    }

    @Override
    public Integer heightForFooterInSection(UITableView tableView, Integer section) {
        return null;
    }

    @Override
    public void didSelectRowAtIndexPath(UITableView tableView, LKIndexPath indexPath) {

    }

    @Override
    public List<LKTableViewRowAction> editActionsForRowAtIndexPath(UITableView tableView, LKIndexPath indexPath) {
        return null;
    }
}
