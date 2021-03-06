package daily.worker.lyberateassignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import daily.worker.lyberateassignment.model.BasicEntity;
import daily.worker.lyberateassignment.model.Title;
import daily.worker.lyberateassignment.model.songsEntity;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    static private RecyclerAdapter recyclerAdapter= new RecyclerAdapter();

    private List<BasicEntity> listWithHeaders;

    public static RecyclerAdapter getRecyclerAdapter(){return recyclerAdapter;}

    public void update_list(List<BasicEntity> listWithHeaders){
        this.listWithHeaders = listWithHeaders;
        notifyDataSetChanged();
    }

    private RecyclerAdapter() {

    }

    @Override
    public int getItemViewType(int position) {
        return listWithHeaders.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return listWithHeaders != null ? listWithHeaders.size() : 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {

            case BasicEntity.GENERAL:
                View v1 = inflater.inflate(R.layout.songs, parent,false);
                viewHolder = new GeneralViewHolder(v1);
                break;

            case BasicEntity.HEADER:
                View v2 = inflater.inflate(R.layout.artist, parent, false);
                viewHolder = new DateViewHolder(v2);
                break;
        }

        assert viewHolder != null;
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        switch (viewHolder.getItemViewType()) {

            case BasicEntity.GENERAL:
                songsEntity generalItem = (songsEntity) listWithHeaders.get(position);
                GeneralViewHolder generalViewHolder = (GeneralViewHolder) viewHolder;
                System.out.println(generalItem.toString());

                //generalViewHolder.tId.setText();
                generalViewHolder.tName.setText("");
                generalViewHolder.tracktimemillis.setText("snjsdij");
                generalViewHolder.primaryGenreName.setText("snjsdij");
                generalViewHolder.artistName.setText(generalItem.getArtistName());
                generalViewHolder.artistId.setText("snjsdij");


                break;

            case BasicEntity.HEADER:
                Title dateItem = (Title) listWithHeaders.get(position);
                DateViewHolder dateViewHolder = (DateViewHolder) viewHolder;
                dateViewHolder.txtTitle.setText(dateItem.getArtist_Name());
                break;
        }
    }



    // ViewHolder for Header
    static class DateViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtTitle;

        public DateViewHolder(View v) {
            super(v);
            this.txtTitle = (TextView) v.findViewById(R.id.textHeader);

        }
    }

    // View holder for general row item
    static class GeneralViewHolder extends RecyclerView.ViewHolder {


        TextView tId,tName,tracktimemillis,primaryGenreName,artistName,artistId;

        public GeneralViewHolder(View v) {
            super(v);

            tId=itemView.findViewById(R.id.textTrackId);
            tName=itemView.findViewById(R.id.textTrackName);
            tracktimemillis=itemView.findViewById(R.id.textTrackTimeMillis);
            primaryGenreName=itemView.findViewById(R.id.textPrimaryGenre);
            artistName=itemView.findViewById(R.id.textArtistName);
            artistId=itemView.findViewById(R.id.textArtistId);

        }
    }

}
