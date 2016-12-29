package net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view.ui_control;


import net.lemonsoft.lemonkit.core_graphics.CGRect;
import net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view.UIView;

/**
 * 所有带有状态控制的控件的父类
 * Created by LiuRi on 2016/12/29.
 */

public class UIControl extends UIView {

    public UIControl() {
    }

    public UIControl(CGRect frame) {
        super(frame);
    }

    public UIControl(Class viewClass) {
        super(viewClass);
    }

}