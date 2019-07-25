package com.example.helloa;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/*3.创建用于描述网络请求的接口
 * Retrofit将Http请求抽象成Java接口：采用注解描述网络请求参数和配置网络请求参数
 * 用动态代理动态将该接口的注解“翻译”成一个Http请求，最后再执行Http请求
 * 注：接口中的每个方法的参数都需要使用注解标注，否则会报错
*/
public interface GetRequest_Interface {
    //第一类注解：网络请求方法
    @GET("openapi.do?keyfrom=Yanzhikai&key=2032414398&type=data&doctype=json&version=1.1&q=car")
    Call<Translation>  getCall();
    // @GET注解的作用:采用Get方法发送网络请求
    // 如果想直接获得Responsebody中的内容，可以定义网络请求返回值为Call<ResponseBody>
    // getCall() = 接收网络请求数据的方法
    // 其中返回类型为Call<*>，*是接收数据的类（即上面定义的Translation类）

    /*Retrofit把 网络请求的URL 分成了两部分设置：
    * // 第1部分：在网络请求接口的注解设置
    *   @GET("openapi.do?keyfrom=Yanzhikai&key=2032414398&type=data&doctype=json&version=1.1&q=car")
    *   Call<Translation>  getCall();
    * 第2部分：在创建Retrofit实例时通过.baseUrl()设置
    *   Retrofit retrofit = new Retrofit.Builder()
    *       .baseUrl("http://fanyi.youdao.com/") //设置网络请求的Url地址
    *       .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
    *       .build();
    *   从上面看出：一个请求的URL可以通过 替换块 和 请求方法的参数 来进行动态的URL更新。
    *   替换块是由 被{}包裹起来的字符串构成
    *   :Retrofit支持动态改变网络请求根目录
    *
    *
    * */
    /**
     * method：网络请求的方法（区分大小写）
     * path：网络请求地址路径
     * hasBody：是否有请求体
     */
    /*
    @HTTP(method = "GET", path = "blog/{id}", hasBody = false)
    Call<ResponseBody> getCall(@Path("id") int id);
    // {id} 表示是一个变量
    // method 的值 retrofit 不会做处理，所以要自行保证准确
    *需要用到OKHttp
    */
    //第二类注解：标记
    //@FormUrlEncoded;@Multipart(适合于有文件上传的情景)
    /**
     *表明是一个表单格式的请求（Content-Type:application/x-www-form-urlencoded）
     * <code>Field("username")</code> 表示将后面的 <code>String name</code> 中name的取值作为 username 的值
     */
    @POST("/form")
    @FormUrlEncoded
    Call<ResponseBody> testFormUrlEncoded1(@Field("username") String name, @Field("age") int age);

    /**
     * {@link Part} 后面支持三种类型，{@link RequestBody}、{@link okhttp3.MultipartBody.Part} 、任意类型
     * 除 {@link okhttp3.MultipartBody.Part} 以外，其它类型都必须带上表单字段({@link okhttp3.MultipartBody.Part} 中已经包含了表单字段的信息)，
     */
    @POST("/form")
    @Multipart
    Call<ResponseBody> testFileUpload1(@Part("name") RequestBody name, @Part("age") RequestBody age, @Part MultipartBody.Part file);
    //三、网络请求参数
    /**
     * Map的key作为表单的键
     */
    @POST("/form")
    @FormUrlEncoded
    Call<ResponseBody> testFormUrlEncoded2(@FieldMap Map<String, Object> map);


    @GET("users/{user}/repos")
    Call<ResponseBody>  getBlog(@Path("user") String user);
    // 访问的API是：https://api.github.com/users/{user}/repos
    // 在发起请求时， {user} 会被替换为方法的第一个参数 user（被@Path注解作用）

    @GET
    Call<ResponseBody> testUrlAndQuery(@Url String url, @Query("showAll") boolean showAll);
    // 当有URL注解时，@GET传入的URL就可以省略
    // 当GET、POST...HTTP等方法中没有设置Url时，则必须使用 {@link Url}提供



}
