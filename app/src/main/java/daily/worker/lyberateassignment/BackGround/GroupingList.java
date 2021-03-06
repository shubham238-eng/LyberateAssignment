package daily.worker.lyberateassignment.BackGround;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import daily.worker.lyberateassignment.RecyclerAdapter;
import daily.worker.lyberateassignment.model.BasicEntity;
import daily.worker.lyberateassignment.model.Title;
import daily.worker.lyberateassignment.model.songsEntity;

public class GroupingList extends AsyncTask<List<songsEntity>, Void, List<BasicEntity>> {

    private RecyclerAdapter recyclerAdapter;

    public  GroupingList(RecyclerAdapter recyclerAdapter) {
        this.recyclerAdapter = recyclerAdapter;
    }

    @SafeVarargs
    @Override
    protected final List<BasicEntity> doInBackground(List<songsEntity>... lists) {




        Map<Integer, List<songsEntity>> groupedHashMap = new HashMap<>();
        Map<Integer, String> artistId_to_name = new HashMap<>();

        for (songsEntity singleResult : lists[0]) {

            String artistName = singleResult.getArtistName();
            Integer artistId = singleResult.getArtistId();

            if (groupedHashMap.containsKey(artistId)) {
                Objects.requireNonNull(groupedHashMap.get(artistId)).add(singleResult);
            } else {

                List<songsEntity> list = new ArrayList<>();
                list.add(singleResult);
                artistId_to_name.put(artistId, artistName);
                groupedHashMap.put(artistId, list);
            }
        }
        List<BasicEntity> result = new ArrayList<>();
        for (Map.Entry<Integer, List<songsEntity>> mapElement : groupedHashMap.entrySet()) {
            Integer key = mapElement.getKey();

            result.add(new Title(artistId_to_name.get(key), key));
            result.addAll(mapElement.getValue());
        }


        return result;
    }

    protected void onPostExecute(List<BasicEntity> lists) {
        recyclerAdapter.update_list(lists);
    }
}
