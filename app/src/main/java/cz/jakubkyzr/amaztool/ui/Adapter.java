package cz.jakubkyzr.amaztool.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import cz.jakubkyzr.amaztool.R;

/**
 * Created by Kubik on 02.11.2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private final Context context;
    private final List<BaseButton> settings;

    public Adapter(Context context, List<BaseButton> settings){
        this.context = context;
        this.settings = settings;
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Three layouts possible - Header, icon and switch
        if (viewType == 0) {
            //Header
            return new ViewHolder(layoutInflater.inflate(R.layout.item_header, parent, false));
        } else if (viewType == 1) {
            //Icon Item
            return new ViewHolder(layoutInflater.inflate(R.layout.item_preference_icon, parent, false));
        }/* else if (viewType == 2) {
            //Switch Item
            return new ViewHolder(layoutInflater.inflate(R.layout.item_preference_switch, parent, false));
        }*/
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        //Return the type of a given item
        BaseButton setting = settings.get(position);
        if (setting instanceof Header) return 0;
        if (setting instanceof IconButton) return 1;
        else return 2;
    }


    @Override
    public void onBindViewHolder(final Adapter.ViewHolder holder, int position) {
        //Get base setting for position
        BaseButton setting = settings.get(position);
        if (setting instanceof Header) {
            //Header, just set text
            holder.title.setText(((Header) setting).title);
        } else {
            //Icon, setup icon, click listener and title
            IconButton IconButton = (IconButton) setting;
            holder.icon.setImageDrawable(IconButton.icon);
            holder.root.setOnClickListener(IconButton.onClickListener);
            holder.title.setText(IconButton.title);
            //Setup subtitle if required
            if (IconButton.subtitle != null) {
                holder.subtitle.setText(IconButton.subtitle);
                holder.subtitle.setVisibility(View.VISIBLE);
            } else {
                holder.subtitle.setText("");
                holder.subtitle.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return settings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View root;
        TextView title, subtitle;
        ImageView icon;
        //Switch sw;

        public ViewHolder(View itemView) {
            super(itemView);
            //Set views
            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);
            icon = itemView.findViewById(R.id.icon);
            //sw = itemView.findViewById(R.id.sw);
            root = itemView;
        }
    }
}