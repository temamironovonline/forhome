package com.example.task1classwork;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ReadCars extends AppCompatActivity implements View.OnClickListener {

    Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readcars2);
        String query = String.format("select * from cars");
        Statement statement = null;
        getDataFromSQL();
    }
    public void getDataFromSQL(){
        String query = String.format("select * from cars");
        Statement statement = null;
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(query);
            TableLayout tableLayout = findViewById(R.id.tablelayout);
            while(resultSet.next())
            {
                printAllFromSQL(tableLayout, Integer.parseInt(resultSet.getString(1)), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void printAllFromSQL(TableLayout tableLayout, int idCar, String carName, String carColor, String carPrice) {
        TableRow row = new TableRow(this);
        TextView showCarName = new TextView(this);
            showCarName.setText(carName);
            showCarName.setTextSize(20);
        TextView showCarColor = new TextView(this);
            showCarColor.setText(carColor);
            showCarColor.setTextSize(20);
        TextView showCarPrice = new TextView(this);
            showCarPrice.setText(carPrice);
            showCarPrice.setTextSize(20);
        Button change = new Button(this);
            change.setId(idCar + Integer.parseInt("Изменить"));
            change.setText("Изменить");
        Button delete = new Button(this);
            delete.setId(idCar + Integer.parseInt("Удалить"));
            delete.setText("Удалить");
        row.addView(showCarName);
        row.addView(showCarColor);
        row.addView(showCarPrice);
        row.addView(change);
        row.addView(delete);
        tableLayout.addView(row);
    }

    public void updateDataSQL(){

    }

    @Override
    public void onClick(View v) {

    }
}
