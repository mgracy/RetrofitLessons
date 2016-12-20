package com.mgx.retrofitlesson1.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by glmgracy on 16/12/20.
 */

public class ActivityCollector {
    //初始化活动集合
    public static List<Activity> activities = new ArrayList<Activity>();
    //添加新的活动
    public static void addActivity(Activity activity) {
        //先判断list集合里是否已有该活动
        if(!activities.contains(activity)){
            activities.add(activity);
        }

    }
    //移除指定的活动
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }
    //移除所有的活动
    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
