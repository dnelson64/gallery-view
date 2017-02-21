package biz.davidnelson.gallery.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;

import java.util.ArrayList;

public class MediaStoreUtils {

    @WorkerThread
    public static ArrayList<String> getAllMediaPath(@NonNull final Context context) {

        final ArrayList<String> listOfAllMedia = new ArrayList<>();
        String absolutePathOfImage;
        int columnIndexData;

        // add images
        final Uri uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        final String[] projection = { MediaStore.MediaColumns.DATA };
        final Cursor cursor = context.getContentResolver().query(uri, projection, null,
            null, null);

        if (cursor != null) {
            columnIndexData = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            while (cursor.moveToNext()) {
                absolutePathOfImage = cursor.getString(columnIndexData);

                listOfAllMedia.add(absolutePathOfImage);
            }
            cursor.close();
        }

        // add video thumbnails
        final Uri videoUri = MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI;
        final String[] videoProjection = { MediaStore.MediaColumns.DATA };
        final Cursor videoCursor = context.getContentResolver().query(videoUri, videoProjection, null,
            null, null);

        if (videoCursor != null) {
            columnIndexData = videoCursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            while (videoCursor.moveToNext()) {
                absolutePathOfImage = videoCursor.getString(columnIndexData);
                Log.d("debug", absolutePathOfImage);

                listOfAllMedia.add(absolutePathOfImage);
            }
            videoCursor.close();
        }

        return listOfAllMedia;
    }


}
