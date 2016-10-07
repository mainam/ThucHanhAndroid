package xyz.imm.uploadfile;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int PICKFILE_REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("file/*");
        startActivityForResult(intent, PICKFILE_REQUEST_CODE);
    }

    public enum TypePermission {
        PERMISSION_READ_CONNTACT(Manifest.permission.READ_CONTACTS),
        PERMISSION_READ_EXTERNAL_STORAGE(android.Manifest.permission.READ_EXTERNAL_STORAGE),
        PERMISSION_WRIRE_EXTERNAL_STORAGE(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
        PERMISSION_ACCESS_FINE_LOCATION(Manifest.permission.ACCESS_FINE_LOCATION),
        PERMISSION_ACCESS_COARSE_LOCATION(Manifest.permission.ACCESS_COARSE_LOCATION),
        CALL_PHONE(Manifest.permission.CALL_PHONE);

        String permission = "";

        TypePermission(String permission) {
            this.permission = permission;
        }

        public String getValue() {
            return permission;
        }
    }

    public static boolean mayRequestPermission(final Context activity, final TypePermission... typePermissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (activity == null)
            return false;

        ArrayList<String> needRequests = new ArrayList<>();
        for (TypePermission typePermission : typePermissions) {
            if (activity.checkSelfPermission(typePermission.getValue()) != PackageManager.PERMISSION_GRANTED) {
                if (activity instanceof Activity && ((Activity) activity).shouldShowRequestPermissionRationale(typePermission.getValue())) {
                    needRequests.add(typePermission.getValue());
                } else {
                    needRequests.add(typePermission.getValue());
                }
            }
        }
        if (needRequests.size() > 0) {
            String[] stockArr = new String[needRequests.size()];
            stockArr = needRequests.toArray(stockArr);
            if (activity instanceof Activity)
                ((Activity) activity).requestPermissions(stockArr, 1000);
        } else
            return true;

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if (resultCode == RESULT_OK && requestCode == PICKFILE_REQUEST_CODE) {
            mayRequestPermission(this, TypePermission.PERMISSION_READ_EXTERNAL_STORAGE);
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    try {
                        Uri temp = data.getData();
                        Log.d(TAG, "doInBackground: " + upload("http://isoracdyt.azurewebsites.net/Post/UploadImageBlob", new File("/sdcard/download/test.jpg")));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            }.execute();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private final OkHttpClient client = new OkHttpClient();

    public String upload(String url, File file) throws IOException {
        RequestBody formBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(),
                        RequestBody.create(MediaType.parse("image/png"), file))
                .addFormDataPart("file", file.getName(),
                        RequestBody.create(MediaType.parse("image/png"), file))
                .addFormDataPart("file", file.getName(),
                        RequestBody.create(MediaType.parse("image/png"), file))
                .addFormDataPart("file", file.getName(),
                        RequestBody.create(MediaType.parse("image/png"), file))
                .addFormDataPart("file", file.getName(),
                        RequestBody.create(MediaType.parse("image/png"), file))
                .addFormDataPart("file", file.getName(),
                        RequestBody.create(MediaType.parse("image/png"), file))
                .build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        Response response = this.client.newCall(request).execute();
        String body = response.body().string();
        return body;
    }
}
