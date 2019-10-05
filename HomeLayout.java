package com.dryfire.milkyy.Activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.dryfire.milkyy.R;
import com.google.android.material.button.MaterialButton;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HomeLayout  extends AppCompatActivity {

    private MaterialButton msubscriptionButton;
    private TextView planDescriptionTextView;
    private AlertDialog.Builder dialogBuilder,aboutusbuilder;
    private Dialog dialog,aboutusdialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mky_home_layout);

        Toolbar home_toolbar = findViewById(R.id.mky_Home_toolbar);
        setSupportActionBar(home_toolbar);
        msubscriptionButton = (MaterialButton) findViewById(R.id.mky_subscriptionButton);
        aboutusbuilder = new AlertDialog.Builder(this);

        msubscriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatingpopUp();


            }

            private void creatingpopUp() {
                dialogBuilder = new AlertDialog.Builder(HomeLayout.this);

                final View view = getLayoutInflater().inflate(R.layout.subscription_plan,null);
                planDescriptionTextView = (TextView) view.findViewById(R.id.mky_plan_300ml);

                planDescriptionTextView.setText("this is 300 ml fucker.this is 500 ml button. motherfucker Background of innovation:\n" +
                        "\tTo give rights of farmers. Because they're the living god on earth. They always grew food for us. Now it's our time to pay them back by spreading awareness about their rights. In th proposed work we have designed a new platform in which will keep monitoring of different deals and prices of the raw material which is grown by the farmer. Most of the time we've seen farmers do suicide because they didn't get the desired of their raw material. Different issues like right price, which grain should be grown next year can be done by this software.\n" +
                        "\n" +
                        "\n" +
                        "Problem Statement:\n" +
                        "\tIn most of the time we saw that the farmer is not really happy about the price he got for his crop. He also leads him to depression because he took loan from the bank for the farming. But because of lack of knowledge about the price and government schemes he was unable to take that benefit. In fact, every time we saw this season the price of potato is high and the price of onion is less and vice-versa in the next season this also happens because of lack of knowledge.\n" +
                        "\n" +
                        "\n" +
                        "Methodology:\n" +
                        "\n" +
                        "1. Deal with farmer \n" +
                        "2. How much land area he have\n" +
                        "3. What kind of crop he grows\n" +
                        "4. How much he invested in that. So that, we'll understand in which part of India we should sell his crop.\n" +
                        "5. Organising online auction \n" +
                        "6. Maintaining transparency. So that, the farmer will have full knowledge of the deal\n" +
                        "7. Farmer can also offer transportation and can also charge f");
                dialogBuilder.setView(view);
                dialog = dialogBuilder.create();
                dialog.show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mky_toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mky_sign_out:
                Toast.makeText(this, "SignOut clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mky_about_us:
                View aboutusview = getLayoutInflater().inflate(R.layout.about_us,null);
                TextView aboutus = (TextView) aboutusview.findViewById(R.id.mky_about_us_textView);
                aboutus.setText("Heap - \n" +
                        "\tA heap is a speacial Tree-based data structure in which the tree is a complete binary tree. Generally Heaps can be of two types :\n" +
                        "a) Min Heap\n" +
                        "b) Max Heap\n" +
                        "\n" +
                        "Min Heap - \n" +
                        "In a Mix Heap the key present at the root node must be minimum among the keys present at all of it's chlidren. The same proeprty must be recursively true for all sub-trees in that binary tree.\n" +
                        "\n" +
                        "Max Heap - \n" +
                        "In a Ma Heap the key present at the root node must be maximum among the keys present at all of it's children. The same property must be recursively true for all sub-trees in that binary tree.\n" +
                        "\n" +
                        "\n" +
                        "Min Heap\t\n" +
                        "\t\t\t\t1\n" +
                        "\t\t\t       / \\\n" +
                        "\t\t\t      3   4\n" +
                        "\t\t\t     / \\  / \\\n" +
                        "\t\t\t    5  6 7   8\n" +
                        "\n" +
                        "Max Heap\n" +
                        "\t\t\t\t8\n" +
                        "\t\t\t       / \\\n" +
                        "\t\t\t      7\t  6\n" +
                        "\t\t\t     / \\  / \n" +
                        "\t\t\t    3   2 1");
                aboutusbuilder.setView(aboutusview);
                aboutusdialog = aboutusbuilder.create();
                aboutusdialog.show();
                Toast.makeText(this, "About Us clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mky_send_feedback:
                Toast.makeText(this, "Send Feedback Clicked", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
