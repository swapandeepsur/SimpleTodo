package com.example.swapan.simpletodo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.ctsuser1.simpletodo.R;

/**
 * Created by swapan on 2/10/17.
 */

public class EditTodoDialogFragment extends DialogFragment implements View.OnClickListener {

    private EditText etEditItem;
    private int editPosition;
    public EditTodoDialogFragment() {
        // Empty constructor is required for DialogFragment
     }

    public static EditTodoDialogFragment newInstance(String todoItem, int position) {
        EditTodoDialogFragment frag = new EditTodoDialogFragment();
        Bundle args = new Bundle();
        args.putString("editItem", todoItem);
        args.putInt("position", position);
        frag.setArguments(args);
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View rootView =   inflater.inflate(R.layout.fragment_edit_item, container);
        getDialog().setTitle("Edit Todo");
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etEditItem = (EditText) view.findViewById(R.id.etEditItem);
        String editItem = getArguments().getString("editItem");
        editPosition = getArguments().getInt("position", 0);

        etEditItem.setText(editItem);
        etEditItem.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);


        Button saveButton = (Button) view.findViewById(R.id.button);
        saveButton.setOnClickListener(this);
    }

    // 1. Defines the listener interface with a method passing back data result.
    public interface EditTodoDialogListener {
        void onFinishEditDialog(String editText, int editPosition);
    }

    // Fires whenever the Save button is clicked
    @Override
    public void onClick(View v) {
            EditTodoDialogListener listener = (EditTodoDialogListener) getActivity();
            listener.onFinishEditDialog(etEditItem.getText().toString(), editPosition);
            // Close the dialog and return back to the parent activity
            dismiss();
    }




}
