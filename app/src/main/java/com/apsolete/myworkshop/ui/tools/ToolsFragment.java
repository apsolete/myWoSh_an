package com.apsolete.myworkshop.ui.tools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;

import com.apsolete.myworkshop.R;
import com.apsolete.customui.CustomFragment;

public class ToolsFragment extends CustomFragment<ToolsViewModel>
{
    public ToolsFragment()
    {
        super(R.layout.fragment_tools, 0, ToolsViewModel.class);
    }

    @Override
    public ToolsViewModel getViewModel()
    {
        return mViewModel;
    }

//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
//    {
//        View root = createView(inflater, container, savedInstanceState, ToolsViewModel.class);
//        setTextViewObserver(R.id.text_tools, mViewModel.getText());
//        return root;
//    }
}