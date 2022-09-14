package com.example.task1classwork;


import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ReadCars extends AppCompatActivity {

    Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readcars);
        String query = String.format("select * from cars");
        Statement statement = null;
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(query);
            int columns = resultSet.getMetaData().getColumnCount();
            resultSet.last();
            int rows = resultSet.getRow();
            resultSet.beforeFirst();
            while (resultSet.next())
            {

                        TableLayout tableLayout = findViewById(R.id.tablelayout);
                        TextView showCarName = findViewById(R.id.showCarName);
                        TextView showCarColor = findViewById(R.id.showCarColor);
                        TextView showCarPrice = findViewById(R.id.showCarPrice);
                        String SCN = resultSet.getString(1);
                        String SCC = resultSet.getString(2);
                        String SCP = resultSet.getString(3);
                        showCarName.setText(SCN);
                        showCarColor.setText(SCC);
                        showCarPrice.setText(SCP);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
