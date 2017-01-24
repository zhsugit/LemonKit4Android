package net.lemonsoft.lemonkit.samples.view_controller;

import android.graphics.Color;
import android.os.Handler;
import android.view.View;

import net.lemonsoft.lemonkit.core.graphics.CGPoint;
import net.lemonsoft.lemonkit.native_ui.extend.view.LKTableView;
import net.lemonsoft.lemonkit.native_ui.extend.view.LKTableViewCell;
import net.lemonsoft.lemonkit.native_ui.model.LKIndexPath;
import net.lemonsoft.lemonkit.native_ui.tools.LKSizeTool;
import net.lemonsoft.lemonkit.ui_kit.adapter.UITableViewDelegateAdapter;
import net.lemonsoft.lemonkit.ui_kit.delegate.UITableViewDataSource;
import net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view.UITableView;
import net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view.UITableViewCell;
import net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view_controller.UIViewController;


/**
 * Created by zhsu on 2017/1/13.
 */

public class TableViewDemoViewController extends UIViewController {

//    @Override
//    public void viewDidLoad() {
//        super.viewDidLoad();
//        final UITableView tableView = new UITableView(LKSizeTool.getDefaultSizeTool().screenFrame());
//        this.view.addSubView(tableView);
//        tableView.setDelegate(new UITableViewDelegateAdapter() {
//            @Override
//            public Integer heightForRowAtIndexPath(UITableView tableView, LKIndexPath indexPath) {
//                return 200;
//            }
//
//            @Override
//            public Integer heightForHeaderInSection(UITableView tableView, Integer section) {
//                return 100;
//            }
//
//            @Override
//            public Integer heightForFooterInSection(UITableView tableView, Integer section) {
//                return 100;
//            }
//        });
//        tableView.setDataSource(new UITableViewDataSource() {
//                                    @Override
//                                    public Integer numberOfRowsInSection(UITableView tableView, Integer section) {
//                                        return 6;
//                                    }
//
//                                    @Override
//                                    public UITableViewCell cellForRowAtIndexPath(UITableView tableView, LKIndexPath indexPath) {
//                                        String identifier = String.format("%d_%d", indexPath.section, indexPath.row);
//                                        UITableViewCell cell = tableView.dequeueReusableCellWithIdentifier(identifier);
//                                        if (cell == null) {// 复用机制里面没有，新创建
//                                            cell = new UITableViewCell(identifier);
//                                            cell.setBackgroundColor(Color.argb(255, (int) (Math.random() * 255.0), (int) (Math.random() * 255.0), (int) (Math.random() * 255.0)));
//                                        }
//                                        if (indexPath.row == 0) {
//                                            cell.removeAllViews();
////                    cell.addView(view);
//                                        }
//                                        return cell;
//                                    }
//
//                                    @Override
//                                    public Integer numberOfSectionsInTableView(UITableView tableView) {
//                                        return 6;
//                                    }
//
//                                    @Override
//                                    public String titleForHeaderInSection(UITableView tableView, Integer section) {
//                                        return "title" + section;
//                                    }
//
//                                    @Override
//                                    public String titleForFooterInSection(UITableView tableView, Integer section) {
//                                        return null;
//                                    }
//
//                                    @Override
//                                    public View viewForHeaderInSection(UITableView tableView, Integer section) {
//                                        return null;
//                                    }
//
//                                    @Override
//                                    public View viewForFooterInSection(UITableView tableView, Integer section) {
//                                        return null;
//                                    }
//
//                                    @Override
//                                    public Integer numberOfRowsInSection(LKTableView tableView, Integer section) {
//                                        return null;
//                                    }
//
//                                    @Override
//                                    public LKTableViewCell cellForRowAtIndexPath(LKTableView tableView, LKIndexPath indexPath) {
//                                        return null;
//                                    }
//
//                                    @Override
//                                    public Integer numberOfSectionsInTableView(LKTableView tableView) {
//                                        return null;
//                                    }
//
//                                    @Override
//                                    public String titleForHeaderInSection(LKTableView tableView, Integer section) {
//                                        return null;
//                                    }
//
//                                    @Override
//                                    public String titleForFooterInSection(LKTableView tableView, Integer section) {
//                                        return null;
//                                    }
//
//                                    @Override
//                                    public View viewForHeaderInSection(LKTableView tableView, Integer section) {
//                                        return null;
//                                    }
//
//                                    @Override
//                                    public View viewForFooterInSection(LKTableView tableView, Integer section) {
//                                        return null;
//                                    }
//                                }
//
//        );
//
//    }
}
//    UIScrollView scrollView = new UIScrollView(LKSizeTool.getDefaultSizeTool().screenFrame());
//scrollView.setContentSize(CGSize.make(0, 1000));
//        this.view.addSubView(scrollView);
//        scrollView.setDelegate(new UIScrollViewDelegateAdapter() {
//@Override
//public void scrollViewDidScroll(UIScrollView scrollView) {
//        super.scrollViewDidScroll(scrollView);
//        System.out.println(-1);
//        System.out.println(scrollView.getContentOffset().y);
//        }
//        });
//        }
//        }