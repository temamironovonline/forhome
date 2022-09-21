package com.example.task1classwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// http://davidmd.ru/2011/12/16/%D1%81%D0%BE%D0%B7%D0%B4%D0%B0%D0%B5%D0%BC-%D1%81%D0%B2%D0%BE%D0%B9-%D0%BA%D0%B0%D1%81%D1%82%D0%BE%D0%BC%D0%BD%D1%8B%D0%B9-listview/
public class MainActivity extends AppCompatActivity {

    Connection connection;
    String connectionResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTextToSQL();

    }
    public void setTextToSQL()
    {
        EditText carName = findViewById(R.id.carName);
        EditText carColor = findViewById(R.id.carColor);
        EditText carPrice = findViewById(R.id.carPrice);
        Button addCarButton = findViewById(R.id.addButton);
        Button readCarButton = findViewById(R.id.readButton);
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();

            if(connection != null)
            {
                addCarButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String CN = carName.getText().toString();
                        String CC = carColor.getText().toString();
                        String CP = carPrice.getText().toString();
                        String query = String.format("insert into cars(NAME_CAR, COLOR_CAR, CAR_PRICE) values ('%s', '%s', %s)", CN, CC, CP);
                        Statement statement = null;
                        try {
                            statement = connection.createStatement();
                            ResultSet resultSet = statement.executeQuery(query);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    }
                });

                readCarButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, ReadCars.class);
                        startActivity(intent);
                    }
                });



            }
            else connectionResult = "Check connection!";
        }

        catch (Exception e)
        {
            Log.e("Error: ", e.getMessage());
        }

    }


}