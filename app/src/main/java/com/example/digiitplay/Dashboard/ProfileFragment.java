package com.example.digiitplay.Dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.digiitplay.DbHandler;
import com.example.digiitplay.EncryptDecrypt;
import com.example.digiitplay.R;
import com.scottyab.aescrypt.AESCrypt;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    TextView score, accuracy, date;
    EncryptDecrypt aes = new EncryptDecrypt();
    DbHandler db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        db = new DbHandler(getActivity());

        ArrayList<String> arr = db.getDecryptedData();

        score = view.findViewById(R.id.score);
        accuracy = view.findViewById(R.id.accuracy);
        date = view.findViewById(R.id.date);

        score.setText(aes.decrypt(arr.get(0)));
        accuracy.setText(aes.decrypt(arr.get(1)));
        date.setText(aes.decrypt(arr.get(2)));

        return view;
    }
}