package com.example.luy.maninvan;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


/**
 * Created by Lulalulali on 11/15/17.
 */

public class JobViewHolder extends RecyclerView.ViewHolder {
    public CardView jobCard;
    public TextView jobStart;
    public TextView jobEnd;
    public TextView jobPrice;
    public TextView jobRoom;
    private Context context;



    public JobViewHolder(View itemView,final Context context) {
        super(itemView);
        jobCard = (CardView) itemView.findViewById(R.id.job_card);
        jobStart = (TextView) itemView.findViewById(R.id.job_start);
        jobEnd = (TextView) itemView.findViewById(R.id.job_end);
        jobRoom = (TextView) itemView.findViewById(R.id.job_rooms);
        jobPrice = (TextView) itemView.findViewById(R.id.job_price);
        this.context = context;
    }

    public void bind(final Job job) {
        jobStart.setText((job.startTime).toString());
        jobEnd.setText(job.endTime.toString());
        jobRoom.setText(job.movingRoom);
        jobPrice.setText(job.price);

//        TODO:Click Thumbnail and POST data.
//        jobCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), GoodsActivity.class);
////                intent.putExtra("GOOD",good);
//                intent.putExtra("EXDE",job.departureArea);
//                intent.putExtra("EXAR",job.arrivalArea);
//                intent.putExtra("EXDA",job.datePreferred);
//                intent.putExtra("EXWH",job.whatToCarry);
//                intent.putExtra("EXFL",job.flexibility);
//                intent.putExtra("EXEM",job.userEmail);
//                intent.putExtra("EXPO",job.photo);
//                v.getContext().startActivity(intent);
//            }

//        });
    }
}
