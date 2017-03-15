package com.example.aleksandar.mysqldemo.PROBA;


        import android.content.DialogInterface;
        import android.database.Cursor;
        import android.os.Bundle;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.util.SparseBooleanArray;
        import android.view.View;
        import android.widget.AbsListView;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.aleksandar.mysqldemo.NumberDatabse;
        import com.example.aleksandar.mysqldemo.R;

        import java.util.ArrayList;

public class DialogList extends AppCompatActivity {

    Button mOrder;
    TextView mItemSelected;
    String[] listItems;
    String[] listItems1;
    boolean[] checkedItems;
    boolean[] checkedItems1;

    NumberDatabse numberDatabse;
    ListView brojevi;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    ArrayList<String> theList2;
    Cursor cursor;
    Cursor cursor1;
    ArrayList<String> theList;
    int idx;
    int id;
    int broj;
    int name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);

        mOrder = (Button) findViewById(R.id.btnOrder);
        mItemSelected = (TextView) findViewById(R.id.tvItemSelected);
        numberDatabse = new NumberDatabse(this);

        theList = new ArrayList<>();
        theList2 = new ArrayList<>();
        cursor = numberDatabse.list_all_list();
        while (cursor.moveToNext()) {

            theList.add(cursor.getString(2));
            listItems = theList.toArray(new String[0]);
            theList2.add(cursor.getString(1));
            listItems1 = theList2.toArray(new String[0]);

        }








        checkedItems = new boolean[listItems.length];
        checkedItems1 = new boolean[listItems1.length];

        mOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(DialogList.this);
                mBuilder.setTitle("");
                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
//                        if (isChecked) {
//                            if (!mUserItems.contains(position)) {
//                                mUserItems.add(position);
//                            }
//                        } else if (mUserItems.contains(position)) {
//                            mUserItems.remove(position);
//                        }
                    /*    int a = position;
                        cursor.moveToPosition(a);
                      String bla =  cursor.getString(1);
                        Toast.makeText(getApplicationContext(), bla,
                                Toast.LENGTH_LONG).show();*/

                        if(isChecked){

                            mUserItems.add(position);
                           /* String bla =  cursor.getString(1);
                            Toast.makeText(getApplicationContext(), bla,
                                    Toast.LENGTH_LONG).show();*/

                        }else{
                            mUserItems.remove((Integer.valueOf(position)));
                        }
                    }
                });

                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String item = "";
                        for (int i = 0; i < mUserItems.size(); i++) {
                            item = item + listItems1[mUserItems.get(i)];
                            if (i != mUserItems.size() - 1) {
                                item = item + ", ";
                            }
                        }
                        mItemSelected.setText(item);
                    }
                });

                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                mBuilder.setNeutralButton("Clear", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        for (int i = 0; i < checkedItems.length; i++) {
                            checkedItems[i] = false;
                            mUserItems.clear();
                            mItemSelected.setText("");
                        }
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });
    }


}