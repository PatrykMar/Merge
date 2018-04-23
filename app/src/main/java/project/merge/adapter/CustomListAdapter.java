package project.merge.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import project.merge.R;
import project.merge.model.Items;

public class CustomListAdapter extends BaseAdapter {

    private ArrayList<Items> itemsList;
    private Context context;
    private LayoutInflater inflater;

    public CustomListAdapter (ArrayList<Items> list,Context cont){
        this.itemsList = list;
        this.context = cont;
    }

    @Override
    public int getCount(){
        return this.itemsList.size();
    }

    @Override
    public Object getItem(int position){
        return this.itemsList.get(position);
    }

    @Override
    public long getItemId(int i){
        return i;
    }

    @Override
    public View getView(int position, View scoreView, ViewGroup parent){
        ViewHolder holder = null;
        if(inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(scoreView == null)
        {
            scoreView = inflater.inflate(R.layout.list_row,parent,false);
            holder = new ViewHolder();
            holder.name = (TextView) scoreView.findViewById(R.id.name);
            holder.score = (TextView) scoreView.findViewById(R.id.score);

            scoreView.setTag(holder);
        }else{
            holder = (ViewHolder) scoreView.getTag();
        }
        Items m = itemsList.get(position);
        holder.name.setText(m.getName());
        holder.score.setText(m.getScore());

        return scoreView;
    }
    static class ViewHolder{
        TextView name;
        TextView score;
    }

}
