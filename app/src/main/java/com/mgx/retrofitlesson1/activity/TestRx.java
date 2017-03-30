package com.mgx.retrofitlesson1.activity;
import com.mgx.retrofitlesson1.util.LogUtil;

import io.reactivex.*;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by glmgracy on 17/3/28.
 */

public class TestRx {
    public static void main(String[] args) {
//        Flowable.just("Hello world").subscribe(System.out::println());
        Flowable.just("Hello world")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        System.out.println(s);
                        LogUtil.i(this.getClass().getSimpleName(), "accept: TaskID: " + s + ", ThreadId: " + Thread.currentThread().getId());
                    }
                });
    }

    public static String aaa(){
//        Flowable.fromCallable(() -> {
//            Thread.sleep(1000);
//            return "Done";
//        });
        return "aaaa";
    }
}
