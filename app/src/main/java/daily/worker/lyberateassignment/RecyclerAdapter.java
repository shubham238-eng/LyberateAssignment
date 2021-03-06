package daily.worker.lyberateassignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import daily.worker.lyberateassignment.model.BasicEntity;
import daily.worker.lyberateassignment.model.SongsEntity;
import daily.worker.lyberateassignment.model.Title;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    static private RecyclerAdapter recyclerAdapter= new RecyclerAdapter();

    private List<BasicEntity> basicEntities;

    public static RecyclerAdapter getRecyclerAdapter(){return recyclerAdapter;}

    public void update_list(List<BasicEntity> listWithHeaders){
        this.basicEntities = listWithHeaders;
        notifyDataSetChanged();
    }

    private RecyclerAdapter() {

    }

    @Override
    public int getItemViewType(int position) {
        return basicEntities.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return basicEntities != null ? basicEntities.size() : 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {

            case BasicEntity.GENERAL:
                View v1 = inflater.inflate(R.layout.songs, parent,false);
                viewHolder = new songViewHolder(v1);
                break;

            case BasicEntity.HEADER:
                View v2 = inflater.inflate(R.layout.artist, parent, false);
                viewHolder = new TitleViewHolder(v2);
                break;
        }

        assert viewHolder != null;
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        switch (viewHolder.getItemViewType()) {

            case BasicEntity.GENERAL:
                SongsEntity songsEntity = (SongsEntity) basicEntities.get(position);
                songViewHolder viewHolder1 = (songViewHolder) viewHolder;
                System.out.println(songsEntity.toString());

                //viewHolder1.tId.setText();
                viewHolder1.tName.setText(Integer.toString(songsEntity.getTrackId()));
                viewHolder1.track_time_mill.setText(Integer.toString(songsEntity.getTrackTimeMillis()));
                viewHolder1.primaryGenreName.setText(songsEntity.getPrimaryGenreName());

                break;

            case BasicEntity.HEADER:
                Title title = (Title) basicEntities.get(position);
                TitleViewHolder viewHolder2 = (TitleViewHolder) viewHolder;
                viewHolder2.txtTitle.setText(title.getArtist_Name() +  "( " + title.getArtist_Id() + " )");
                break;
        }
    }



    // Header class
    static class TitleViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtTitle;

        public TitleViewHolder(View v) {
            super(v);
            this.txtTitle = (TextView) v.findViewById(R.id.textHeader);

        }
    }

    // songs entity class
    static class songViewHolder extends RecyclerView.ViewHolder {


        TextView tId,tName,track_time_mill,primaryGenreName;

        public songViewHolder(View v) {
            super(v);

            tId=itemView.findViewById(R.id.TrackId);
            tName=itemView.findViewById(R.id.TrackName);
            track_time_mill=itemView.findViewById(R.id.TrackTimeMillis);
            primaryGenreName=itemView.findViewById(R.id.PrimaryGenre);

        }
    }

}
