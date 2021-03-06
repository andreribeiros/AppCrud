package br.com.mrdroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class Detalhe extends AppCompatActivity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE =10;
    private ImageView imgVi;
    private Button btnCamera;
    private Button btnEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        btnCamera = findViewById(R.id.btnCamera);
        btnEditar = findViewById(R.id.btnEditar);
        imgVi = findViewById(R.id.imgFunc);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCamera();
            }
        });
    }


    public void abrirCamera(){
        Intent inten = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        String arquivo = Environment.getExternalStorageDirectory() + "/" + System.currentTimeMillis() + ".jpg";

        File file = new File(arquivo);

        Uri outputFileUri = Uri.fromFile(file);


        inten.putExtra("arquivo", arquivo);
        startActivityForResult(inten,CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);


        /*if(file.exists()){
            Bitmap foto = BitmapFactory.decodeFile(file.getAbsolutePath());
            imgVi.setImageBitmap(foto);
        }*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        System.gc();
        if(requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE){
            if(resultCode ==RESULT_OK){
                try{
                    //BitmapFactory.decodeFile(data.getExtras().getString("arquivo"));
                    Uri urlLocal = data.getData();

                    Bitmap foto = MediaStore.Images.Media.getBitmap(getContentResolver(),urlLocal);


                    imgVi.setImageBitmap(foto);
                   // dialog = new AlertDialog.Builder(MainActivit.this);
                   // dialog.setMessage("ASDF?:" +data.getExtras().getString("arquivo"));
                   // dialog.show();

                }catch (Exception e){
                    //dialog = new AlertDialog.Builder(MainActivit.this);
                   // dialog.setMessage("Ocorreu um erro no app ao tirar a foto !\n detalhes do erro : "+e.getMessage());
                   // dialog.show();
                }
            }
        }
    }
}
