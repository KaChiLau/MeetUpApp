package com.example.kachi.storyfinderfinale.adapter;

/**
 * Created by KaChi on 2016/12/11.
 */

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.kachi.storyfinderfinale.app.App;
import com.example.kachi.storyfinderfinale.model.Event;
import com.example.kachi.storyfinderfinale.R;
import com.example.kachi.storyfinderfinale.volley.VolleySingleton;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Event> eventList;
    private OnClickListener listener;

    public RecyclerViewAdapter(List<Event> eventList) {
        this.eventList = eventList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_card_layout, parent, false);
        return new CardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Event event = eventList.get(position);

        CardViewHolder cardViewHolder = (CardViewHolder) holder;
        cardViewHolder.setName(event.getName());
        cardViewHolder.setPoster(event.getPoster());
        //cardViewHolder.setLat(event.getLat());
        cardViewHolder.setRating(event.getRating());
        cardViewHolder.setDescription(event.getDescription());
        //cardViewHolder.setLon(event.getLon());
        if(listener!=null) {
            cardViewHolder.bindClickListener(listener, event);
        }
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public void updateDataSet(List<Event> modelList) {
        this.eventList.clear();
        this.eventList.addAll(modelList);
        notifyDataSetChanged();

    }

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnClickListener {
        void onCardClick(Event event);
        void onPosterClick(Event event);
    }

    private class CardViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView name;
        private TextView description;
        private TextView rating;
        private TextView lon;
        private NetworkImageView poster;


        CardViewHolder(View view) {
            super(view);
            this.cardView = (CardView) view.findViewById(R.id.card_view);
            this.name = (TextView) view.findViewById(R.id.tvTitle);
            this.poster = (NetworkImageView) view.findViewById(R.id.nivPoster);
            this.rating = (TextView) view.findViewById(R.id.rating);
            this.description = (TextView) view.findViewById(R.id.description);
            //this.lat = (TextView) view.findViewById(R.id.lat);
            //this.lon = (TextView) view.findViewById(R.id.lon);

        }

        void setName(String name) {
            this.name.setText(name);
        }

        void setLon (String lon) {
            String n = "Lon: " + lon;
            this.lon.setText(n);
        }

        void setRating (String rating) {
            String n = "Rating: " + rating;
            this.rating.setText(n);
        }

        void setDescription(String description) {
            this.description.setText("Description: " + fixDescription(description));
        }

        void setPoster(String imageUrl) {
            ImageLoader imageLoader = VolleySingleton.getInstance(App.getContext()).getImageLoader();
            this.poster.setImageUrl(imageUrl, imageLoader);
        }

        public String fixDescription(String description) {
            String str = description.replaceAll("<p>", "");
            str = str.replaceAll("</p>", "");
            str = str.replaceAll("<br>", "");
            str = str.replaceAll("<span>", "");
            str = str.replaceAll("</span>", "");
            str = str.replaceAll("<strong>", "");
            str = str.replaceAll("</strong>", "");
            str = str.replaceAll("_", "");

            if(str.length() > 500) {
                str = str.substring(0,500) + "....";
            }

            return str;
        }


        void bindClickListener(final OnClickListener listener, final Event event){
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onCardClick(event);
                }
            });

        }
    }

}
