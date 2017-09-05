package exampleprilognew.ru.films.view.Cinema;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressManager {

    private ProgressDialog progressDialog;


    public ProgressManager(Context context) {

        this.progressDialog = new ProgressDialog(context);
    }


    public void showDialog(){


        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        progressDialog.show();
    }

public void dismissDialog() {

    if (progressDialog.isShowing()) {

        progressDialog.dismiss();
    }
}
}
