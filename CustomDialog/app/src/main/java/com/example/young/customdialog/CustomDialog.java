package com.example.young.customdialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class CustomDialog extends Dialog {

    TextView positiveBtn;
    ImageButton negativeBtn;

    public CustomDialog(Context context) {
        super(context);
        //requestWindowFeature(Window.FEATURE_NO_TITLE); //다이얼로그의 타이틀바를 없애줌
        //getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //다이얼로그의 배경을 투명하게
        setContentView(R.layout.custom_dialog);

        positiveBtn = (TextView) findViewById(R.id.positiveBtn);
        negativeBtn = (ImageButton) findViewById(R.id.negativeBtn);

        positiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "상세보기", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        negativeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }

}
