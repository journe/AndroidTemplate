package tech.jour.template.base.utils

import android.os.Build.VERSION.SDK_INT
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import tech.jour.template.base.BaseApplication

/**
 * 用于加载 Gif 的 Coil ImageLoader
 *
 * @since 2021/9/6 4:26 下午
 */
object CoilGIFImageLoader {

	val imageLoader = ImageLoader.Builder(BaseApplication.context)
		.components {
			if (SDK_INT >= 28) {
				add(ImageDecoderDecoder.Factory())
			} else {
				add(GifDecoder.Factory())
			}
		}
		.build()
}