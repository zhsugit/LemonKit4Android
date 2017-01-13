package net.lemonsoft.lemonkit.ui_kit.delegate.LKUITableView;

import net.lemonsoft.lemonkit.native_ui.extend.view.LKTableView;
import net.lemonsoft.lemonkit.native_ui.model.LKIndexPath;
import net.lemonsoft.lemonkit.native_ui.model.LKUITableViewRowAction;

import java.util.List;

/**
 * 代理接口
 */
public interface LKUITableViewDelegate {
    // Variable height support
    Integer heightForRowAtIndexPath(LKTableView tableView, LKIndexPath indexPath);

    Integer heightForHeaderInSection(LKTableView tableView, Integer section);

    Integer heightForFooterInSection(LKTableView tableView, Integer section);

    void didSelectRowAtIndexPath(LKTableView tableView, LKIndexPath indexPath);

    List<LKUITableViewRowAction> editActionsForRowAtIndexPath(LKTableView tableView, LKIndexPath indexPath);
}