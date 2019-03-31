package com.nhatdo.whatisthis.TutorialDialogFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nhatdo.whatisthis.R;

public class DFFlashCardTest extends DialogFragment {

    Button btnOkInFCQuiz;

    public DFFlashCardTest(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tutorial_flashcard_test, container, false);
        getDialog().setTitle("How To Play!");
        btnOkInFCQuiz = view.findViewById(R.id.btnOk);
        btnOkInFCQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        return view;
    }
}
