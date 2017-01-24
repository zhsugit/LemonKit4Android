package net.lemonsoft.lemonkit.samples.view_controller;

import android.graphics.Color;

import android.icu.util.Measure;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import net.lemonsoft.lemonkit.native_ui.extend.adapter.LKTableViewDataSourceAdapter;
import net.lemonsoft.lemonkit.native_ui.extend.adapter.LKTableViewDelegateAdapter;
import net.lemonsoft.lemonkit.native_ui.extend.view.LKTableView;
import net.lemonsoft.lemonkit.native_ui.extend.view.LKTableViewCell;
import net.lemonsoft.lemonkit.native_ui.model.LKIndexPath;
import net.lemonsoft.lemonkit.native_ui.model.LKTableViewRowAction;
import net.lemonsoft.lemonkit.native_ui.tools.LKSizeTool;
import net.lemonsoft.lemonkit.samples.R;
import net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view_controller.UIViewController;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class MainViewController extends UIViewController {
    /**
     * 房源列表
     */
    ArrayList<AllRentListDto> houseList;

    @Override
    public void viewDidLoad() {
        super.viewDidLoad();


        houseList = getDataSouce();
        LKTableView tableView = new LKTableView(this);

        this.view.addView(tableView);
        final RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        tableView.setLayoutParams(layoutParams);

        tableView.delegate = new LKTableViewDelegateAdapter() {
            @Override
            public void didSelectRowAtIndexPath(LKTableView tableView, LKIndexPath indexPath) {
                System.out.println("卧槽，被点急了：" + indexPath.section + " r : " + indexPath.row);
            }

            @Override
            public Integer heightForRowAtIndexPath(LKTableView tableView, LKIndexPath indexPath) {
                return 400;
            }

            @Override
            public List<LKTableViewRowAction> editActionsForRowAtIndexPath(LKTableView tableView, LKIndexPath indexPath) {
                List<LKTableViewRowAction> list = new ArrayList<>();
                // 创建侧滑收藏布局
                RelativeLayout relativeLayout = new RelativeLayout(MainViewController.this);
                RelativeLayout.LayoutParams laParam = new RelativeLayout.LayoutParams(LKSizeTool.getDefaultSizeTool().DP(120), LKSizeTool.getDefaultSizeTool().DP(120));
                relativeLayout.setLayoutParams(laParam);
                relativeLayout.measure(0, 0);
                //收藏图标
                ImageView imageView = new ImageView(MainViewController.this);
                imageView.setBackgroundResource(R.mipmap.bg_all_rent_detail_colleciton1);
                imageView.measure(0, 0);
                float relativeLayoutw = relativeLayout.getMeasuredWidth();
                float relativeLayouth = relativeLayout.getMeasuredHeight();
                imageView.setX((relativeLayout.getX() + relativeLayoutw / 2) - imageView.getMeasuredWidth() / 2);
                imageView.setY((relativeLayout.getY() + relativeLayouth / 2) - imageView.getMeasuredHeight());
                relativeLayout.addView(imageView);

                //收藏文字
                TextView textView = new TextView(MainViewController.this);
                textView.setText("收藏房源");
                textView.setTextSize(18f);
                textView.setTextColor(Color.WHITE);
                textView.measure(0, 0);
                //收藏文字位置
                textView.setX((relativeLayout.getX() + relativeLayoutw / 2) - textView.getMeasuredWidth() / 2);
                textView.setY((relativeLayout.getY() + relativeLayouth / 2));
                relativeLayout.addView(textView);
                list.add(new LKTableViewRowAction(relativeLayout, LKSizeTool.getDefaultSizeTool().dpToPx(120f), ContextCompat.getColor(MainViewController.this, R.color.app_bg_color), new LKTableViewRowAction.TouchAction() {

                    @Override
                    public void onTouch(LKTableView tableView, LKTableViewRowAction rowAction, LKIndexPath indexPath) {
                        Toast.makeText(MainViewController.this, "点击了----title:" + houseList.get(indexPath.row).getTitleA(), Toast.LENGTH_SHORT).show();
                    }
                }));
                return list;

            }
        };

        tableView.dataSource = new LKTableViewDataSourceAdapter() {

            @Override
            public Integer numberOfSectionsInTableView(LKTableView tableView) {
                return 1;
            }

            @Override
            public Integer numberOfRowsInSection(LKTableView tableView, Integer section) {
                return houseList.size();
            }

            @Override
            public LKTableViewCell cellForRowAtIndexPath(LKTableView tableView, LKIndexPath indexPath) {
//                String identifier = String.format("%d_%d", indexPath.section, indexPath.row);
                String identifier = houseList.get(indexPath.row).getId();
//                Log.e("bu1111111创建","创建" + indexPath.row);
                LKTableViewCell cell = tableView.dequeueReusableCellWithIdentifier(identifier);

                if (cell == null) {// 复用机制里面没有，新创建
                    Log.e("创建", "创建" + indexPath.row);
                    cell = new LKTableViewCell(MainViewController.this, identifier);
                    LayoutInflater layoutInflater = LayoutInflater.from(MainViewController.this);
                    // 初始化 布局
                    RelativeLayout currentLayout = (RelativeLayout) layoutInflater.inflate(R.layout.list_item_rent_house, null);
                    initViewAndSetData(currentLayout, indexPath.row);
                    cell.addView(currentLayout);
//                    cell.setBackgroundColor(Color.argb(255, (int) (Math.random() * 255.0), (int) (Math.random() * 255.0), (int) (Math.random() * 255.0)));
                }
                if (indexPath.row == 0) {
//                    cell.removeAllViews();
//                    cell.addView(view);
                }
                Log.e("获取控件：", "=============" + indexPath.row);
                return cell;
            }


//            @Override
//            public String titleForHeaderInSection(LKTableView tableView, Integer section) {
//                return "title" + section;
//            }

            @Override
            public View viewForHeaderInSection(LKTableView tableView, Integer section) {
                LinearLayout linearLayout = new LinearLayout(MainViewController.this);
                linearLayout.setPadding(10, 10, 0, 10);
                //红色方块
                View hotview = new View(MainViewController.this);
                hotview.setBackgroundColor(Color.RED);
                hotview.setPadding(10, 0, 0, 0);
                hotview.setLayoutParams(new ViewGroup.LayoutParams(LKSizeTool.getDefaultSizeTool().DP(3f), ViewGroup.LayoutParams.MATCH_PARENT));
                linearLayout.addView(hotview);
                //title
                TextView textView = new TextView(MainViewController.this);
                textView.setText("公共房源");
                textView.setPadding(30, 0, 0, 0);
                textView.setTextSize(14);
                linearLayout.addView(textView);

//                View line=new View(MainViewController.this);
//                line.setBackgroundColor(ContextCompat.getColor(MainViewController.this,R.color.gray6));
//                linearLayout.addView(line);
                return linearLayout;
            }


//            @Override
//            public String titleForFooterInSection(LKUITableView tableView, Integer section) {
//                return "footer =>" + section;
//            }

            @Override
            public View viewForFooterInSection(final LKTableView tableView, Integer section) {
                String identifier = String.format("%d_%d", 0, section);
                View myView = tableView.dequeueReusableFooterWithIdentifier(identifier);
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

    /**
     * 获取房源列表数据
     */
    public ArrayList<AllRentListDto> getDataSouce() {
        ArrayList<AllRentListDto> myData = new ArrayList<AllRentListDto>();
        String dataStr = "[\n" +
                "    {\n" +
                "        \"id\": \"46486fe838c1441ab9f212e37728cff5\",\n" +
                "        \"titleB\": \"整租 · 0室2厅0卫 · 120㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2016-12-12/75ECD84F2F78192F96E54DABBD2CEBB8_middle.jpg\",\n" +
                "        \"certFlag\": \"\",\n" +
                "        \"titleA\": \"西岗区 鞍山路\",\n" +
                "        \"rent\": \"3500.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"01\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"5cff035308894e04898a1f08226029ba\",\n" +
                "        \"titleB\": \"合租 · 1室1厅0卫 · 110㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2017-01-11/0F663C8EDD3E0433ABD8F4E20F2377DE_middle.jpg\",\n" +
                "        \"certFlag\": \"\",\n" +
                "        \"titleA\": \"西岗区 奥林匹克广场\",\n" +
                "        \"rent\": \"11.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"d076c1fb494f42f99d91389f231a2d06\",\n" +
                "        \"titleB\": \"整租 · 1室1厅1卫 · 99㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2017-01-10/3f99bd19-02d3-4c73-bfba-2cf4dd3938e7_middle.jpg\",\n" +
                "        \"certFlag\": \"\",\n" +
                "        \"titleA\": \"中山区 长江路\",\n" +
                "        \"rent\": \"1000.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"24188af6fa9c46e89c3deec3edc9cadd\",\n" +
                "        \"titleB\": \"整租 · 2室1厅2卫 · 150㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2017-01-10/bf1b2367-4d45-4278-91bf-15498dc5303a_middle.jpg\",\n" +
                "        \"certFlag\": \"\",\n" +
                "        \"titleA\": \"中山区 友好广场\",\n" +
                "        \"rent\": \"3000.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"234697a7090348229ce141cdb0db09df\",\n" +
                "        \"titleB\": \"整租 · 3室1厅1卫 · 97㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2017-01-11/be77b05e-86e8-4448-88e0-ff8c21dce2f3_middle.jpg\",\n" +
                "        \"certFlag\": \"\",\n" +
                "        \"titleA\": \"沙河口区 春柳\",\n" +
                "        \"rent\": \"1000.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"69607de190bd479db98f4d3145c5d4cb\",\n" +
                "        \"titleB\": \"整租 · 2室1厅1卫 · 120㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2016-12-08/8c609393-e839-48e8-b018-d2988a88099f_middle.jpg\",\n" +
                "        \"certFlag\": \"\",\n" +
                "        \"titleA\": \"甘井子区 金三角\",\n" +
                "        \"rent\": \"3600.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"01\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"a9e3cf98f1eb43ad8c97a9c96f13b5fa\",\n" +
                "        \"titleB\": \"整租 · 3室1厅2卫 · 120㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2016-12-08/9a029a4e-39ec-4847-95a0-4c5fe091d014_middle.jpg\",\n" +
                "        \"certFlag\": \"1\",\n" +
                "        \"titleA\": \"沙河口区 南沙\",\n" +
                "        \"rent\": \"3600.00 元/月\",\n" +
                "        \"feature\": \"认证房源\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"01\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"d3c593a45fe748bb9607df60ee27e1c5\",\n" +
                "        \"titleB\": \"合租 · 3室1厅2卫 · 130㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2016-12-08/22bf55ed-fb8a-4683-b489-4d96b99caa97_middle.jpg\",\n" +
                "        \"certFlag\": \"1\",\n" +
                "        \"titleA\": \"沙河口区 绿波\",\n" +
                "        \"rent\": \"1600.00 元/月\",\n" +
                "        \"feature\": \"认证房源\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"01\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"5313351eaa3040368ecfbfdedffb563d\",\n" +
                "        \"titleB\": \"整租 · 3室2厅2卫 · 150㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2016-12-08/c2a63ca8-fac8-4192-82bf-b731945aa99c_middle.jpg\",\n" +
                "        \"certFlag\": \"1\",\n" +
                "        \"titleA\": \"甘井子区 红旗中路\",\n" +
                "        \"rent\": \"3500.00 元/月\",\n" +
                "        \"feature\": \"认证房源\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"01\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"ca49d9edacc74308a22939ab5b2ecccb\",\n" +
                "        \"titleB\": \"整租 · 3室2厅2卫 · 150㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2016-12-08/25572513-3c08-4712-a224-fa095a5f6a42_middle.jpg\",\n" +
                "        \"certFlag\": \"0\",\n" +
                "        \"titleA\": \"沙河口区 民权\",\n" +
                "        \"rent\": \"3500.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"01\"\n" +
                "    },\n" + "    {\n" +
                "        \"id\": \"46486fe838c1441ab9f212e37728cff5\",\n" +
                "        \"titleB\": \"整租 · 0室2厅0卫 · 120㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2016-12-12/75ECD84F2F78192F96E54DABBD2CEBB8_middle.jpg\",\n" +
                "        \"certFlag\": \"\",\n" +
                "        \"titleA\": \"西岗区 鞍山路\",\n" +
                "        \"rent\": \"3500.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"01\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"5cff035308894e04898a1f08226029ba\",\n" +
                "        \"titleB\": \"合租 · 1室1厅0卫 · 110㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2017-01-11/0F663C8EDD3E0433ABD8F4E20F2377DE_middle.jpg\",\n" +
                "        \"certFlag\": \"\",\n" +
                "        \"titleA\": \"西岗区 奥林匹克广场\",\n" +
                "        \"rent\": \"11.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"d076c1fb494f42f99d91389f231a2d06\",\n" +
                "        \"titleB\": \"整租 · 1室1厅1卫 · 99㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2017-01-10/3f99bd19-02d3-4c73-bfba-2cf4dd3938e7_middle.jpg\",\n" +
                "        \"certFlag\": \"\",\n" +
                "        \"titleA\": \"中山区 长江路\",\n" +
                "        \"rent\": \"1000.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"24188af6fa9c46e89c3deec3edc9cadd\",\n" +
                "        \"titleB\": \"整租 · 2室1厅2卫 · 150㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2017-01-10/bf1b2367-4d45-4278-91bf-15498dc5303a_middle.jpg\",\n" +
                "        \"certFlag\": \"\",\n" +
                "        \"titleA\": \"中山区 友好广场\",\n" +
                "        \"rent\": \"3000.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"234697a7090348229ce141cdb0db09df\",\n" +
                "        \"titleB\": \"整租 · 3室1厅1卫 · 97㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2017-01-11/be77b05e-86e8-4448-88e0-ff8c21dce2f3_middle.jpg\",\n" +
                "        \"certFlag\": \"\",\n" +
                "        \"titleA\": \"沙河口区 春柳\",\n" +
                "        \"rent\": \"1000.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"69607de190bd479db98f4d3145c5d4cb\",\n" +
                "        \"titleB\": \"整租 · 2室1厅1卫 · 120㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2016-12-08/8c609393-e839-48e8-b018-d2988a88099f_middle.jpg\",\n" +
                "        \"certFlag\": \"\",\n" +
                "        \"titleA\": \"甘井子区 金三角\",\n" +
                "        \"rent\": \"3600.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"01\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"a9e3cf98f1eb43ad8c97a9c96f13b5fa\",\n" +
                "        \"titleB\": \"整租 · 3室1厅2卫 · 120㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2016-12-08/9a029a4e-39ec-4847-95a0-4c5fe091d014_middle.jpg\",\n" +
                "        \"certFlag\": \"1\",\n" +
                "        \"titleA\": \"沙河口区 南沙\",\n" +
                "        \"rent\": \"3600.00 元/月\",\n" +
                "        \"feature\": \"认证房源\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"01\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"d3c593a45fe748bb9607df60ee27e1c5\",\n" +
                "        \"titleB\": \"合租 · 3室1厅2卫 · 130㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2016-12-08/22bf55ed-fb8a-4683-b489-4d96b99caa97_middle.jpg\",\n" +
                "        \"certFlag\": \"1\",\n" +
                "        \"titleA\": \"沙河口区 绿波\",\n" +
                "        \"rent\": \"1600.00 元/月\",\n" +
                "        \"feature\": \"认证房源\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"01\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"5313351eaa3040368ecfbfdedffb563d\",\n" +
                "        \"titleB\": \"整租 · 3室2厅2卫 · 150㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2016-12-08/c2a63ca8-fac8-4192-82bf-b731945aa99c_middle.jpg\",\n" +
                "        \"certFlag\": \"1\",\n" +
                "        \"titleA\": \"甘井子区 红旗中路\",\n" +
                "        \"rent\": \"3500.00 元/月\",\n" +
                "        \"feature\": \"认证房源\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"01\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"ca49d9edacc74308a22939ab5b2ecccb\",\n" +
                "        \"titleB\": \"整租 · 3室2厅2卫 · 150㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2016-12-08/25572513-3c08-4712-a224-fa095a5f6a42_middle.jpg\",\n" +
                "        \"certFlag\": \"0\",\n" +
                "        \"titleA\": \"沙河口区 民权\",\n" +
                "        \"rent\": \"3500.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"01\"\n" +
                "    },\n" + "    {\n" +
                "        \"id\": \"46486fe838c1441ab9f212e37728cff5\",\n" +
                "        \"titleB\": \"整租 · 0室2厅0卫 · 120㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2016-12-12/75ECD84F2F78192F96E54DABBD2CEBB8_middle.jpg\",\n" +
                "        \"certFlag\": \"\",\n" +
                "        \"titleA\": \"西岗区 鞍山路\",\n" +
                "        \"rent\": \"3500.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"01\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"5cff035308894e04898a1f08226029ba\",\n" +
                "        \"titleB\": \"合租 · 1室1厅0卫 · 110㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2017-01-11/0F663C8EDD3E0433ABD8F4E20F2377DE_middle.jpg\",\n" +
                "        \"certFlag\": \"\",\n" +
                "        \"titleA\": \"西岗区 奥林匹克广场\",\n" +
                "        \"rent\": \"11.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"d076c1fb494f42f99d91389f231a2d06\",\n" +
                "        \"titleB\": \"整租 · 1室1厅1卫 · 99㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2017-01-10/3f99bd19-02d3-4c73-bfba-2cf4dd3938e7_middle.jpg\",\n" +
                "        \"certFlag\": \"\",\n" +
                "        \"titleA\": \"中山区 长江路\",\n" +
                "        \"rent\": \"1000.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"24188af6fa9c46e89c3deec3edc9cadd\",\n" +
                "        \"titleB\": \"整租 · 2室1厅2卫 · 150㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2017-01-10/bf1b2367-4d45-4278-91bf-15498dc5303a_middle.jpg\",\n" +
                "        \"certFlag\": \"\",\n" +
                "        \"titleA\": \"中山区 友好广场\",\n" +
                "        \"rent\": \"3000.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"234697a7090348229ce141cdb0db09df\",\n" +
                "        \"titleB\": \"整租 · 3室1厅1卫 · 97㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2017-01-11/be77b05e-86e8-4448-88e0-ff8c21dce2f3_middle.jpg\",\n" +
                "        \"certFlag\": \"\",\n" +
                "        \"titleA\": \"沙河口区 春柳\",\n" +
                "        \"rent\": \"1000.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"69607de190bd479db98f4d3145c5d4cb\",\n" +
                "        \"titleB\": \"整租 · 2室1厅1卫 · 120㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2016-12-08/8c609393-e839-48e8-b018-d2988a88099f_middle.jpg\",\n" +
                "        \"certFlag\": \"\",\n" +
                "        \"titleA\": \"甘井子区 金三角\",\n" +
                "        \"rent\": \"3600.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"01\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"a9e3cf98f1eb43ad8c97a9c96f13b5fa\",\n" +
                "        \"titleB\": \"整租 · 3室1厅2卫 · 120㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2016-12-08/9a029a4e-39ec-4847-95a0-4c5fe091d014_middle.jpg\",\n" +
                "        \"certFlag\": \"1\",\n" +
                "        \"titleA\": \"沙河口区 南沙\",\n" +
                "        \"rent\": \"3600.00 元/月\",\n" +
                "        \"feature\": \"认证房源\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"01\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"d3c593a45fe748bb9607df60ee27e1c5\",\n" +
                "        \"titleB\": \"合租 · 3室1厅2卫 · 130㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2016-12-08/22bf55ed-fb8a-4683-b489-4d96b99caa97_middle.jpg\",\n" +
                "        \"certFlag\": \"1\",\n" +
                "        \"titleA\": \"沙河口区 绿波\",\n" +
                "        \"rent\": \"1600.00 元/月\",\n" +
                "        \"feature\": \"认证房源\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"01\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"5313351eaa3040368ecfbfdedffb563d\",\n" +
                "        \"titleB\": \"整租 · 3室2厅2卫 · 150㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2016-12-08/c2a63ca8-fac8-4192-82bf-b731945aa99c_middle.jpg\",\n" +
                "        \"certFlag\": \"1\",\n" +
                "        \"titleA\": \"甘井子区 红旗中路\",\n" +
                "        \"rent\": \"3500.00 元/月\",\n" +
                "        \"feature\": \"认证房源\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"01\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"ca49d9edacc74308a22939ab5b2ecccb\",\n" +
                "        \"titleB\": \"整租 · 3室2厅2卫 · 150㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2016-12-08/25572513-3c08-4712-a224-fa095a5f6a42_middle.jpg\",\n" +
                "        \"certFlag\": \"0\",\n" +
                "        \"titleA\": \"沙河口区 民权\",\n" +
                "        \"rent\": \"3500.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"01\"\n" +
                "    },\n" + "    {\n" +
                "        \"id\": \"46486fe838c1441ab9f212e37728cff5\",\n" +
                "        \"titleB\": \"整租 · 0室2厅0卫 · 120㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2016-12-12/75ECD84F2F78192F96E54DABBD2CEBB8_middle.jpg\",\n" +
                "        \"certFlag\": \"\",\n" +
                "        \"titleA\": \"西岗区 鞍山路\",\n" +
                "        \"rent\": \"3500.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"01\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"5cff035308894e04898a1f08226029ba\",\n" +
                "        \"titleB\": \"合租 · 1室1厅0卫 · 110㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2017-01-11/0F663C8EDD3E0433ABD8F4E20F2377DE_middle.jpg\",\n" +
                "        \"certFlag\": \"\",\n" +
                "        \"titleA\": \"西岗区 奥林匹克广场\",\n" +
                "        \"rent\": \"11.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"d076c1fb494f42f99d91389f231a2d06\",\n" +
                "        \"titleB\": \"整租 · 1室1厅1卫 · 99㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2017-01-10/3f99bd19-02d3-4c73-bfba-2cf4dd3938e7_middle.jpg\",\n" +
                "        \"certFlag\": \"\",\n" +
                "        \"titleA\": \"中山区 长江路\",\n" +
                "        \"rent\": \"1000.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"24188af6fa9c46e89c3deec3edc9cadd\",\n" +
                "        \"titleB\": \"整租 · 2室1厅2卫 · 150㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2017-01-10/bf1b2367-4d45-4278-91bf-15498dc5303a_middle.jpg\",\n" +
                "        \"certFlag\": \"\",\n" +
                "        \"titleA\": \"中山区 友好广场\",\n" +
                "        \"rent\": \"3000.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"234697a7090348229ce141cdb0db09df\",\n" +
                "        \"titleB\": \"整租 · 3室1厅1卫 · 97㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2017-01-11/be77b05e-86e8-4448-88e0-ff8c21dce2f3_middle.jpg\",\n" +
                "        \"certFlag\": \"\",\n" +
                "        \"titleA\": \"沙河口区 春柳\",\n" +
                "        \"rent\": \"1000.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"69607de190bd479db98f4d3145c5d4cb\",\n" +
                "        \"titleB\": \"整租 · 2室1厅1卫 · 120㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2016-12-08/8c609393-e839-48e8-b018-d2988a88099f_middle.jpg\",\n" +
                "        \"certFlag\": \"\",\n" +
                "        \"titleA\": \"甘井子区 金三角\",\n" +
                "        \"rent\": \"3600.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"01\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"a9e3cf98f1eb43ad8c97a9c96f13b5fa\",\n" +
                "        \"titleB\": \"整租 · 3室1厅2卫 · 120㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2016-12-08/9a029a4e-39ec-4847-95a0-4c5fe091d014_middle.jpg\",\n" +
                "        \"certFlag\": \"1\",\n" +
                "        \"titleA\": \"沙河口区 南沙\",\n" +
                "        \"rent\": \"3600.00 元/月\",\n" +
                "        \"feature\": \"认证房源\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"01\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"d3c593a45fe748bb9607df60ee27e1c5\",\n" +
                "        \"titleB\": \"合租 · 3室1厅2卫 · 130㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2016-12-08/22bf55ed-fb8a-4683-b489-4d96b99caa97_middle.jpg\",\n" +
                "        \"certFlag\": \"1\",\n" +
                "        \"titleA\": \"沙河口区 绿波\",\n" +
                "        \"rent\": \"1600.00 元/月\",\n" +
                "        \"feature\": \"认证房源\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"01\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"5313351eaa3040368ecfbfdedffb563d\",\n" +
                "        \"titleB\": \"整租 · 3室2厅2卫 · 150㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2016-12-08/c2a63ca8-fac8-4192-82bf-b731945aa99c_middle.jpg\",\n" +
                "        \"certFlag\": \"1\",\n" +
                "        \"titleA\": \"甘井子区 红旗中路\",\n" +
                "        \"rent\": \"3500.00 元/月\",\n" +
                "        \"feature\": \"认证房源\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"01\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"ca49d9edacc74308a22939ab5b2ecccb\",\n" +
                "        \"titleB\": \"整租 · 3室2厅2卫 · 150㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2016-12-08/25572513-3c08-4712-a224-fa095a5f6a42_middle.jpg\",\n" +
                "        \"certFlag\": \"0\",\n" +
                "        \"titleA\": \"沙河口区 民权\",\n" +
                "        \"rent\": \"3500.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"01\"\n" +
                "    },\n" + "    {\n" +
                "        \"id\": \"46486fe838c1441ab9f212e37728cff5\",\n" +
                "        \"titleB\": \"整租 · 0室2厅0卫 · 120㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2016-12-12/75ECD84F2F78192F96E54DABBD2CEBB8_middle.jpg\",\n" +
                "        \"certFlag\": \"\",\n" +
                "        \"titleA\": \"西岗区 鞍山路\",\n" +
                "        \"rent\": \"3500.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"01\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"5cff035308894e04898a1f08226029ba\",\n" +
                "        \"titleB\": \"合租 · 1室1厅0卫 · 110㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2017-01-11/0F663C8EDD3E0433ABD8F4E20F2377DE_middle.jpg\",\n" +
                "        \"certFlag\": \"\",\n" +
                "        \"titleA\": \"西岗区 奥林匹克广场\",\n" +
                "        \"rent\": \"11.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"d076c1fb494f42f99d91389f231a2d06\",\n" +
                "        \"titleB\": \"整租 · 1室1厅1卫 · 99㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2017-01-10/3f99bd19-02d3-4c73-bfba-2cf4dd3938e7_middle.jpg\",\n" +
                "        \"certFlag\": \"\",\n" +
                "        \"titleA\": \"中山区 长江路\",\n" +
                "        \"rent\": \"1000.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"24188af6fa9c46e89c3deec3edc9cadd\",\n" +
                "        \"titleB\": \"整租 · 2室1厅2卫 · 150㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2017-01-10/bf1b2367-4d45-4278-91bf-15498dc5303a_middle.jpg\",\n" +
                "        \"certFlag\": \"\",\n" +
                "        \"titleA\": \"中山区 友好广场\",\n" +
                "        \"rent\": \"3000.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"234697a7090348229ce141cdb0db09df\",\n" +
                "        \"titleB\": \"整租 · 3室1厅1卫 · 97㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2017-01-11/be77b05e-86e8-4448-88e0-ff8c21dce2f3_middle.jpg\",\n" +
                "        \"certFlag\": \"\",\n" +
                "        \"titleA\": \"沙河口区 春柳\",\n" +
                "        \"rent\": \"1000.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"69607de190bd479db98f4d3145c5d4cb\",\n" +
                "        \"titleB\": \"整租 · 2室1厅1卫 · 120㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2016-12-08/8c609393-e839-48e8-b018-d2988a88099f_middle.jpg\",\n" +
                "        \"certFlag\": \"\",\n" +
                "        \"titleA\": \"甘井子区 金三角\",\n" +
                "        \"rent\": \"3600.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"01\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"a9e3cf98f1eb43ad8c97a9c96f13b5fa\",\n" +
                "        \"titleB\": \"整租 · 3室1厅2卫 · 120㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2016-12-08/9a029a4e-39ec-4847-95a0-4c5fe091d014_middle.jpg\",\n" +
                "        \"certFlag\": \"1\",\n" +
                "        \"titleA\": \"沙河口区 南沙\",\n" +
                "        \"rent\": \"3600.00 元/月\",\n" +
                "        \"feature\": \"认证房源\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"01\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"d3c593a45fe748bb9607df60ee27e1c5\",\n" +
                "        \"titleB\": \"合租 · 3室1厅2卫 · 130㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2016-12-08/22bf55ed-fb8a-4683-b489-4d96b99caa97_middle.jpg\",\n" +
                "        \"certFlag\": \"1\",\n" +
                "        \"titleA\": \"沙河口区 绿波\",\n" +
                "        \"rent\": \"1600.00 元/月\",\n" +
                "        \"feature\": \"认证房源\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"01\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"5313351eaa3040368ecfbfdedffb563d\",\n" +
                "        \"titleB\": \"整租 · 3室2厅2卫 · 150㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2016-12-08/c2a63ca8-fac8-4192-82bf-b731945aa99c_middle.jpg\",\n" +
                "        \"certFlag\": \"1\",\n" +
                "        \"titleA\": \"甘井子区 红旗中路\",\n" +
                "        \"rent\": \"3500.00 元/月\",\n" +
                "        \"feature\": \"认证房源\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"01\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"ca49d9edacc74308a22939ab5b2ecccb\",\n" +
                "        \"titleB\": \"整租 · 3室2厅2卫 · 150㎡\",\n" +
                "        \"imageUrl\": \"http://10.32.156.154:8091/img/lea/2016-12-08/25572513-3c08-4712-a224-fa095a5f6a42_middle.jpg\",\n" +
                "        \"certFlag\": \"0\",\n" +
                "        \"titleA\": \"沙河口区 民权\",\n" +
                "        \"rent\": \"3500.00 元/月\",\n" +
                "        \"feature\": \"\",\n" +
                "        \"collectionFlag\": 0,\n" +
                "        \"rentInstallment\": \"01\"\n" +
                "    }\n" +
                "]";
        try {
            JSONArray myJsonArray = new JSONArray(dataStr);
            for (int i = 0; i < myJsonArray.length(); i++) {
                JSONObject myjObject = myJsonArray.getJSONObject(i);
                AllRentListDto allRentListDto = new AllRentListDto();
                allRentListDto.setId(myjObject.getString("id"));
                allRentListDto.setTitleA(myjObject.getString("titleA"));
                allRentListDto.setTitleB(myjObject.getString("titleB"));
//                allRentListDto.setImageUrl(myjObject.getString("imageUrl"));
                allRentListDto.setCertFlag(myjObject.getString("certFlag"));
                allRentListDto.setRent(myjObject.getString("rent"));
                allRentListDto.setFeature(myjObject.getString("feature"));
                allRentListDto.setCollectionFlag(Integer.parseInt(myjObject.getString("collectionFlag")));
                allRentListDto.setRentInstallment(myjObject.getString("rentInstallment"));

                myData.add(allRentListDto);
            }
        } catch (Exception e) {

        }
        return myData;

    }

    public void initViewAndSetData(RelativeLayout currentLayout, int section) {
        //初始化容器组件
        //缩略图
        SimpleDraweeView home_house_icon = (SimpleDraweeView) currentLayout.findViewById(R.id.home_house_icon);
        //标题
        TextView home_house_title = (TextView) currentLayout.findViewById(R.id.home_house_title);
        //属性
        TextView home_house_content = (TextView) currentLayout.findViewById(R.id.home_house_content);
        //价格
        TextView home_house_price = (TextView) currentLayout.findViewById(R.id.home_house_price);
        //是否分期 00：否 01 ：是
        ImageView home_house_rentInstallment = (ImageView) currentLayout.findViewById(R.id.home_house_rentInstallment);
        //房源验真
        ImageView home_house_real_housing_img = (ImageView) currentLayout.findViewById(R.id.home_house_real_housing_img);

        //向组件内添加数据
//        Uri uri = Uri.parse(houseList.get(section).getImageUrl());
//        home_house_icon.setImageURI(uri);

        home_house_title.setText(houseList.get(section).getTitleA());
        home_house_content.setText(houseList.get(section).getTitleB());
        home_house_price.setText(houseList.get(section).getRent());

        // 是否分期 00：否 01 ：是
        String rentInstallment = houseList.get(section).getRentInstallment();
        int visibility = ("01".equals(rentInstallment)) ? View.VISIBLE : View.GONE;
        home_house_rentInstallment.setVisibility(visibility);
        // 是否验真 0：正在验真 ；1：验真成功；2：验真失败
        String realHousing = houseList.get(section).getCertFlag();
        int visibilityRealHousing = ("1".equals(realHousing)) ? View.VISIBLE : View.GONE;
        home_house_real_housing_img.setVisibility(visibilityRealHousing);


    }

//    //缩略图
//    SimpleDraweeView home_house_icon;
//    //标题
//    TextView home_house_title;
//    //属性
//    TextView home_house_content;
//    //价格
//    TextView home_house_price;
//    //是否分期 00：否 01 ：是
//    ImageView home_house_rentInstallment;
//    //房源验真
//    ImageView home_house_real_housing_img;


}

