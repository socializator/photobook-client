package com.freecoders.photobook.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.DisplayMetrics;

import com.freecoders.photobook.common.Photobook;

import java.io.File;

public final class ImageUtils {

	public static final int SMALL_WIDTH = 300;
	
	public static final int MEDIUM_WIDTH = 600;
	
	public static final int LARGE_WIDTH = 1200;
	
	public static final Bitmap resizeBitmap(Bitmap b, int newWidth) {
		int width = b.getWidth();
		int height = b.getHeight();
		
		float scale = (float) newWidth / width;

		Matrix matrix = new Matrix();
		matrix.postScale(scale, scale);
		
		Bitmap resizedBitmap = Bitmap.createBitmap(b, 0, 0, width, height, matrix, false);
		return resizedBitmap;
	}

    public static final Boolean resizeImageFile(File f) {
        return true;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
            int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while (((halfHeight / inSampleSize) > reqHeight)
                    || ((halfWidth / inSampleSize) > reqWidth)) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmap(String imgPath, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imgPath, options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(imgPath, options);
    }

    public final static int dpToPx(int dp) {
        DisplayMetrics displayMetrics = Photobook.getMainActivity().
                getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}
