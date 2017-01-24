package net.lemonsoft.lemonkit.native_ui.extend.adapter;

import android.view.View;

import net.lemonsoft.lemonkit.native_ui.extend.delegate.LKTableViewDataSource;
import net.lemonsoft.lemonkit.native_ui.extend.view.LKTableView;

/**
 * LKUITableView的数据源适配器
 * Created by lemonsoft on 16-10-12.
 */
public abstract class LKTableViewDataSourceAdapter implements LKTableViewDataSource {

    @Override
    public Integer numberOfSectionsInTableView(LKTableView tableView) {
        return 1;
    }

    @Override
    public String titleForHeaderInSection(LKTableView tableView, Integer section) {
        return null;// 返回null表示不显示header
    }

    @Override
    public String titleForFooterInSection(LKTableView tableView, Integer section) {
        return null;// 返回null表示不显示footer
    }

    @Override
    public View viewForHeaderInSection(LKTableView tableView, Integer section) {
        return null;
    }

    @Override
    public View viewForFooterInSection(LKTableView tableView, Integer section) {
        return null;
    }
}
