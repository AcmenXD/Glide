# Glide
基于<a href="https://github.com/bumptech/glide">glide</a>进行一部分简易封装及初始化等, 更易于开发者使用.

如要了解功能实现,请运行app程序查看控制台日志和源代码!
* 源代码 : <a href="https://github.com/AcmenXD/Glide">AcmenXD/Glide</a>
* apk下载路径 : <a href="https://github.com/AcmenXD/Resource/blob/master/apks/Glide.apk">Glide.apk</a>

![jpg](https://github.com/AcmenXD/Glide/blob/master/pic/1.jpg)
![jpg](https://github.com/AcmenXD/Glide/blob/master/pic/2.jpg)
![jpg](https://github.com/AcmenXD/Glide/blob/master/pic/3.jpg)

### 依赖
---
- AndroidStudio
```
	allprojects {
            repositories {
                ...
                maven { url 'https://jitpack.io' }
            }
	}
```
```
	 compile 'com.github.AcmenXD:Glide:1.5'
```
- AndroidManifest.xml
```
    // 添加meta-data部分,Glide才能正确初始化
    <application
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:theme="@style/AppTheme">
        <meta-data
                android:name="com.acmenxd.glide.GlideManager"
                android:value="GlideModule" />
    </application>
```
### 混淆
---
```
     -keep public class * implements com.bumptech.glide.module.GlideModule
     -keep class com.bumptech.glide.** {
         *;
     }
```
### 功能
---
####v1.2 最新版本

####v1.1 修复bug:
- 修复磁盘大小 单位错误问题

####v1.0 支持功能如下
- 使用方式同glide一样
- 提供glide初始的一些函数调用
### 配置
---
**在Application中配置**
```java
/**
 * 设置缓存磁盘大小
 *
 * @param mainCacheSize  大图片磁盘大小(MB) 默认为50MB
 */
GlideManager.setCacheSize(50);
/**
 * 设置缓存图片的存放路径
 * Environment.getExternalStorageDirectory().getAbsolutePath() + "/Glide/"
 *
 * @param cachePath     路径:默认为SD卡根目录Glide下 (此路径非直接存储图片的路径,还需要以下目录设置)
 * @param mainCacheDir  大图片存放目录:默认为MainCache目录
 */
GlideManager.setCachePath(FileUtils.imgCacheDirPath, "MainCache");
/**
 * 设置图片解码格式
 *
 * @param decodeFormat 默认:DecodeFormat.PREFER_RGB_565
 */
GlideManager.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
```
### 使用 -> 以下代码 注释很详细、很重要很重要很重要!!!
---
```java
    Glide.with(this).load(url)
        .placeholder(R.mipmap.ic_launcher) // 占位符（默认显示图片）
        .error(R.mipmap.ic_launcher)       // 错误占位符（当网址错误，或其他原因显示不出来图片时）
        .into(iv)
        .fitCenter()
        .centerCrop()
        .asBitmap()  //显示gif静态图片
        .asGif()     //显示gif动态图片
        .animate(R.anim.item_alpha_in) //设置动画
        .crossFade()       // 渐变动画（淡入淡出效果，默认是300毫秒，当然后边会有自定义动画）
        .dontAnimate()   // 不想要任何动画效果
        .thumbnail(0.1f) //设置缩略图支持
        .override(800, 800)//设置加载尺寸
        .skipMemoryCache(true) //跳过内存缓存
        .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存策略
        .priority(Priority.NORMAL) //下载优先级
        .transform(new RoundTransform(this)) //圆角转化器
        .transform(new CircleTransform(this)) //圆型转化器
        .listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                // 可以用于监控请求发生错误来源
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                imageView.setImageDrawable(resource);
                return false;
            }
        }
        .into(new SimpleTarget<GlideDrawable>() { // 下载图片后可对图片进行修改后在添加到ImageView中
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                imageView.setImageDrawable(resource);
            }
        });
```
---
### 打个小广告^_^
**gitHub** : https://github.com/AcmenXD   如对您有帮助,欢迎点Star支持,谢谢~

**技术博客** : http://blog.csdn.net/wxd_beijing
# END