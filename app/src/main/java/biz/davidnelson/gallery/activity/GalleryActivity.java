package biz.davidnelson.gallery.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import biz.davidnelson.gallery.R;
import biz.davidnelson.gallery.adapter.GalleryAdapter;
import biz.davidnelson.gallery.util.MediaStoreUtils;

public class GalleryActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private static int NUM_GRID_COLUMNS = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        mRecyclerView = (RecyclerView) findViewById(R.id.gallery_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        final RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, NUM_GRID_COLUMNS);
        mRecyclerView.setLayoutManager(mLayoutManager);

        new GetMediaListTask().execute();
    }

    private class GetMediaListTask extends AsyncTask<Void, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(Void... voids) {
            return MediaStoreUtils.getAllMediaPath(getApplicationContext());
        }

        @Override
        protected void onPostExecute(ArrayList<String> imageList) {
            final RecyclerView.Adapter galleryAdapter = new GalleryAdapter(imageList);
            mRecyclerView.setAdapter(galleryAdapter);
        }
    }
 }
