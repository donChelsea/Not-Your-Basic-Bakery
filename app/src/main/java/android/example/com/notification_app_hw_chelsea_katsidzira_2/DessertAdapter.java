package android.example.com.notification_app_hw_chelsea_katsidzira_2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DessertAdapter extends RecyclerView.Adapter<DessertAdapter.DessertViewHolder> {
    private static final String IMAGE_EXTRA = "Image Extra";
    private static final String NAME_EXTRA = "Name Extra";
    private static final String DESC_EXTRA = "Description Extra";
    private List<Integer> dessertImagesList;
    private List<String> dessertNameList;
    private List<String> dessertDescList;
    private Context context;

    public DessertAdapter(Context context, List<Integer> dessertImagesList, List<String> dessertTextList, List<String> dessertDescList) {
        this.dessertImagesList = dessertImagesList;
        this.dessertNameList = dessertTextList;
        this.dessertDescList = dessertDescList;
        this.context = context;
    }

    @NonNull
    @Override
    public DessertViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.dessert_list_view, viewGroup, false);
        return new DessertViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DessertViewHolder dessertViewHolder, final int i) {
        dessertViewHolder.dessertImage.setImageResource(dessertImagesList.get(i));
        dessertViewHolder.dessertText.setText(dessertNameList.get(i));
        dessertViewHolder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Bundle extras = new Bundle();
                    extras.putInt(IMAGE_EXTRA, dessertImagesList.get(i));
                    extras.putString(NAME_EXTRA, dessertNameList.get(i));
                    extras.putString(DESC_EXTRA, dessertDescList.get(i));
                    Intent intent = new Intent(context, OrderActivity.class);
                    intent.putExtras(extras);
                    context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dessertImagesList.size();
    }



    /* VIEW HOLDER */

    public class DessertViewHolder extends RecyclerView.ViewHolder {

        private ImageView dessertImage;
        private TextView dessertText;
        private CardView cardview;

        public DessertViewHolder(@NonNull View itemView) {
            super(itemView);
            this.dessertImage = itemView.findViewById(R.id.dessert_image);
            this.dessertText = itemView.findViewById(R.id.dessert_text);
            this.cardview = itemView.findViewById(R.id.cardview);
        }
    }
}
