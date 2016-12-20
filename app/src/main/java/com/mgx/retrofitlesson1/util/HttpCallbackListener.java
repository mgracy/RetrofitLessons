package com.mgx.retrofitlesson1.util;

/**
 * Created by glmgracy on 16/12/20.
 */

public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
