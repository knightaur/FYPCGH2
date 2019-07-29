package sg.edu.rp.c346.fypcgh;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class ContentAdapter extends ExpandableRecyclerViewAdapter<TitleViewHolder, ContentViewHolder> {
    public ContentAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public TitleViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_recyclerview_title, parent, false);
        return new TitleViewHolder(v);
    }

    @Override
    public ContentViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_recyclerview_content, parent, false);
        return new ContentViewHolder(v);
    }

    @Override
    public void onBindChildViewHolder(ContentViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final Content content = (Content) group.getItems().get(childIndex);
        holder.bind(content);
    }

    @Override
    public void onBindGroupViewHolder(TitleViewHolder holder, int flatPosition, ExpandableGroup group) {
        final Title title = (Title) group;
        holder.bind(title);
    }
}
