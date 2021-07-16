package com.bytedance.practice5;


import com.bytedance.practice5.model.UploadResponse;

import java.util.HashMap;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface IApi {

    //TODO 4
    // 补全所有注解
    @Multipart
    @POST("messages")
    Call<UploadResponse> submitMessage(@Query("student_id") String studentId,
                                       @Query("extra_value") String extraValue,
                                       @Part MultipartBody.Part from,
                                       @Part MultipartBody.Part to,
                                       @Part MultipartBody.Part content,
                                       @Part MultipartBody.Part image,
                                       @Header("token") String token);

    // 从@Body绕了一圈回来……Body只能出现一次，不能和Multipart合用，以及HTTP方法……
    // 这块掌握确实还不大好，好在几小时的琢磨下来理解了很多，同时也万分感谢同学的点拨
    // 以及不仅要看Logcat和Debug，Run里也有很多关键信息
}

