package com.mgx.retrofitlesson1.model.PopupWindowDemo;

import android.content.Context;

/**
 * Created by glmgracy on 17/4/6.
 */

public class Util {
    /**
     * µÃµ½Éè±¸ÆÁÄ»µÄ¿í¶È
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * µÃµ½Éè±¸ÆÁÄ»µÄ¸ß¶È
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * µÃµ½Éè±¸µÄÃÜ¶È
     */
    public static float getScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * °ÑÃÜ¶È×ª»»ÎªÏñËØ
     */
    public static int dip2px(Context context, float px) {
        final float scale = getScreenDensity(context);
        return (int) (px * scale + 0.5);
    }
}
