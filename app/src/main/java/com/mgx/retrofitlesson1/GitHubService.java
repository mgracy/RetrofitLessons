package com.mgx.retrofitlesson1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by glmgr on 2016/12/14.
 */

public interface GitHubService {
    @GET("users/{user}")
    Call<List<Repo>> listRepos(@Path("user") String user);
    @GET("users/{user}")
    Call<Repo> loadRepo(@Path("user") String user);
}
