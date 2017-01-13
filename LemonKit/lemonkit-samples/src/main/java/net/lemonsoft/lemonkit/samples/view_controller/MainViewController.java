package net.lemonsoft.lemonkit.samples.view_controller;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import net.lemonsoft.lemonkit.native_ui.extend.adapter.LKUITableViewDataSourceAdapter;
import net.lemonsoft.lemonkit.native_ui.extend.adapter.LKUITableViewDelegateAdapter;
import net.lemonsoft.lemonkit.native_ui.extend.view.LKTableView;
import net.lemonsoft.lemonkit.native_ui.extend.view.LKUITableViewCell;
import net.lemonsoft.lemonkit.native_ui.model.LKIndexPath;
import net.lemonsoft.lemonkit.native_ui.model.LKUITableViewRowAction;
import net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view_controller.UIViewController;

import java.util.ArrayList;
import java.util.List;


public class MainViewController extends UIViewController {

    @Override
    public void viewDidLoad() {
        super.viewDidLoad();
//        UILabel label = new UILabel(CGRect.make(50, 150, 200, 100));
//        label.setText("Hello! LemonKit World.");
//        label.setBackgroundColor(UIColor.greenColor());
//        this.view.addSubView(label);
//        label.setClipsToBounds(true);
//        label.layer.setBorderWidth(1);
//        label.layer.setCornerRadius(80);
//        label.layer.setBorderColor(UIColor.redColor().cgColor());
//        this.view.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha(0.7f, 0.6f, 0.5f, 0.9f));
//
//        LemonBubble.showRight(this, "hello", 2000);

//        this.view.setBackgroundColor(UIColor.blueColor());
//
//        UIScrollView scrollView = new UIScrollView(LKSizeTool.getDefaultSizeTool().screenFrame());
//        scrollView.setContentSize(CGSize.make(0, 1000));
//        this.view.addSubView(scrollView);
//        UIView view = new UIView(CGRect.make(0, 0, 100, 100));
//        view.setBackgroundColor(UIColor.hotpinkColor());
//        scrollView.addSubView(view);
//        scrollView.setDelegate(new UIScrollViewDelegateAdapter() {
//            @Override
//            public void scrollViewDidScroll(UIScrollView scrollView) {
//                super.scrollViewDidScroll(scrollView);
//                System.out.println(scrollView.getContentOffset().y);
//            }
//        });

        LKTableView tableView = new LKTableView(this);

        this.view.addView(tableView);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1500);
        tableView.setLayoutParams(layoutParams);
//        tableView.setBackgroundColor(Color.argb(255, 238, 238, 238));

        tableView.delegate = new LKUITableViewDelegateAdapter() {
            @Override
            public void didSelectRowAtIndexPath(LKTableView tableView, LKIndexPath indexPath) {
                System.out.println("卧槽，被点急了：" + indexPath.section + " r : " + indexPath.row);
            }

            @Override
            public Integer heightForRowAtIndexPath(LKTableView tableView, LKIndexPath indexPath) {
                return 200;
            }

            @Override
            public List<LKUITableViewRowAction> editActionsForRowAtIndexPath(LKTableView tableView, LKIndexPath indexPath) {
                if (indexPath.row == 0) {
                    List<LKUITableViewRowAction> list = new ArrayList<>();
                    list.add(new LKUITableViewRowAction(getApplicationContext(), "hello", Color.WHITE, Color.RED, new LKUITableViewRowAction.TouchAction() {
                        @Override
                        public void onTouch(LKTableView tableView, LKUITableViewRowAction rowAction, LKIndexPath indexPath) {

                        }
                    }));
                    list.add(new LKUITableViewRowAction(getApplicationContext(), "你好", Color.WHITE, Color.BLUE, new LKUITableViewRowAction.TouchAction() {
                        @Override
                        public void onTouch(LKTableView tableView, LKUITableViewRowAction rowAction, LKIndexPath indexPath) {

                        }
                    }));
                    return list;
                }
                return new ArrayList<>();
            }
        };

        tableView.dataSource = new LKUITableViewDataSourceAdapter() {

            @Override
            public Integer numberOfSectionsInTableView(LKTableView tableView) {
                return 6;
            }

            @Override
            public Integer numberOfRowsInSection(LKTableView tableView, Integer section) {
                return 6;
            }

            @Override
            public LKUITableViewCell cellForRowAtIndexPath(LKTableView tableView, LKIndexPath indexPath) {
                String identifier = String.format("%d_%d", indexPath.section, indexPath.row);
                LKUITableViewCell cell = tableView.dequeueReusableCellWithIdentifier(identifier);
                if (cell == null) {// 复用机制里面没有，新创建
                    cell = new LKUITableViewCell(MainViewController.this, identifier);
                    cell.setBackgroundColor(Color.argb(255, (int) (Math.random() * 255.0), (int) (Math.random() * 255.0), (int) (Math.random() * 255.0)));
                }
                if (indexPath.row == 0) {
                    cell.removeAllViews();
//                    cell.addView(view);
                }
                return cell;
            }


            @Override
            public String titleForHeaderInSection(LKTableView tableView, Integer section) {
                return "title" + section;
            }

//            @Override
//            public String titleForFooterInSection(LKUITableView tableView, Integer section) {
//                return "footer =>" + section;
//            }

            @Override
            public View viewForFooterInSection(final LKTableView tableView, Integer section) {
                String identifier = String.format("%d_%d", 0, section);
                View myView = tableView.dequeueReusableHeaderWithIdentifier(identifier);
                if (myView == null) {
                    Button btn = new Button(MainViewController.this);
                    btn.setText("11111");
                    btn.setBackgroundColor(Color.argb(255, (int) (Math.random() * 255.0), (int) (Math.random() * 255.0), (int) (Math.random() * 255.0)));
                    btn.setTextColor(Color.WHITE);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 2000);

                            tableView.setLayoutParams(layoutParams);

                        }
                    });
                    return btn;
                }
                return myView;
            }
        };


    }


}

