package com.example.basicbankapp;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class ViewHolder extends RecyclerView.ViewHolder{

    TextView mName, mPhonenumber, mBalance, mName1, mDate, mTrans_status;
    ImageView mArrow;
    View mView;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;

        mName = itemView.findViewById(R.id.CustName);
        mPhonenumber = itemView.findViewById(R.id.AccNum);
        mBalance = itemView.findViewById(R.id.AccBal);

        mName1 = itemView.findViewById(R.id.name1);
        mDate = itemView.findViewById(R.id.date);


        mTrans_status = itemView.findViewById(R.id.transaction_status);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });
    }
    private com.example.basicbankapp.ViewHolder.ClickListener mClickListener;
    public interface  ClickListener{
        void onItemClick();

        void onItemClick(View view, int position);
    }

    public void setOnClickListener(com.example.basicbankapp.ViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }
}
