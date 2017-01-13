package net.lemonsoft.lemonkit.native_ui.extend.adapter;

import net.lemonsoft.lemonkit.native_ui.extend.view.LKTableView;
import net.lemonsoft.lemonkit.native_ui.model.LKIndexPath;
import net.lemonsoft.lemonkit.native_ui.model.LKUITableViewRowAction;
import net.lemonsoft.lemonkit.ui_kit.delegate.LKUITableView.LKUITableViewDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * LKUITableView的代理适配器
 * Created by lemonsoft on 16-10-12.
 */
public abstract class LKUITableViewDelegateAdapter implements LKUITableViewDelegate {

    @Override
    public Integer heightForRowAtIndexPath(LKTableView tableView, LKIndexPath indexPath) {
        return 140;
    }

    @Override
    public Integer heightForHeaderInSection(LKTableView tableView, Integer section) {
        return 80;
    }

    @Override
    public Integer heightForFooterInSection(LKTableView tableView, Integer section) {
        return 80;
    }

    @Override
    public void didSelectRowAtIndexPath(LKTableView tableView, LKIndexPath indexPath) {
    }

    @Override
    public List<LKUITableViewRowAction> editActionsForRowAtIndexPath(LKTableView tableView, LKIndexPath indexPath) {
        return new ArrayList<>();
    }
}
