package com.apsolete.workshop.ui.wsentity.types;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.apsolete.customui.Adapters;
import com.apsolete.customui.CustomFragment;
import com.apsolete.customui.ScrollChildSwipeRefreshLayout;
import com.apsolete.workshop.R;
import com.apsolete.workshop.db.entities.WsEntityType;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class TypesFragment extends CustomFragment<TypesViewModel>
{
    private TypesAdapter mTypesAdapter;

    public TypesFragment()
    {
        super(R.layout.fragment_equipmenttypes, R.string.title_equipmenttypes, TypesViewModel.class);
    }

    @Override
    public TypesViewModel getViewModel()
    {
        return mViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mTypesAdapter = new TypesAdapter();
        mViewModel.getEquipTypes().observe(this, new Observer<List<WsEntityType>>()
        {
            @Override
            public void onChanged(List<WsEntityType> wsEntityTypes)
            {
                mTypesAdapter.setList(wsEntityTypes);
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View root = super.onCreateView(inflater, container, savedInstanceState);
        ListView listView = root.findViewById(R.id.list_types);
        listView.setAdapter(mTypesAdapter);

        // Set up progress indicator
        final ScrollChildSwipeRefreshLayout swipeRefreshLayout = root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(listView);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                mViewModel.load();
            }
        });

        return root;
    }

    private static class TypesAdapter extends Adapters.ItemsAdapter<WsEntityType>
    {
        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            View rowView = view;
            if (rowView == null) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                rowView = inflater.inflate(R.layout.item_equipmenttype, viewGroup, false);
            }
            WsEntityType wsEntityType = getItem(i);

            TextView textTypeId = (TextView) rowView.findViewById(R.id.text_equipmenttype_id);
            textTypeId.setText(Long.toString(wsEntityType.id));

            TextView textTypeName = (TextView) rowView.findViewById(R.id.text_equipmenttype_name);
            textTypeName.setText(wsEntityType.name);

            TextView textTypeDescr = (TextView) rowView.findViewById(R.id.text_equipmenttype_descr);
            textTypeDescr.setText(wsEntityType.description);

            return rowView;
        }
    }
}