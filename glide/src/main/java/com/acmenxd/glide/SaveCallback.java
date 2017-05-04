package com.acmenxd.glide;

import java.io.File;

/**
 * @author AcmenXD
 * @version v1.0
 * @github https://github.com/AcmenXD
 * @date 2017/5/4 15:56
 * @detail 保存图片的回调接口
 */
public interface SaveCallback {

    void succeed(final File file);

    void failed(final Exception pE);
}
