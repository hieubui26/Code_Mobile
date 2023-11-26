package com.mad.g1.bui_minh_hieu.code_mobile.ViewHolder;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mad.g1.bui_minh_hieu.code_mobile.R;



public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ViewHolderListener viewHolderListener;
    private TextView txtName, txtDate, txtStatus, txtLevel, txtDescription;

    public RecyclerViewHolder(@NonNull View itemView, ViewHolderListener viewHolderListener) {
        super(itemView);
        txtName = itemView.findViewById(R.id.txtName);
        txtDescription = itemView.findViewById(R.id.txtDescription);
        txtDate = itemView.findViewById(R.id.txtDate);
        txtLevel = itemView.findViewById(R.id.txtLevel);
        txtStatus = itemView.findViewById(R.id.txtStatus);

        this.viewHolderListener = viewHolderListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (this.viewHolderListener != null) {
            this.viewHolderListener.onClickViewHolder(view, getAdapterPosition());
        }
    }

    public TextView getTxtName() {
        return txtName;
    }

    public void setTxtName(TextView txtName) {
        this.txtName = txtName;
    }

    public TextView getTxtDate() {
        return txtDate;
    }

    public void setTxtDate(TextView txtDate) {
        this.txtDate = txtDate;
    }

    public TextView getTxtStatus() {
        return txtStatus;
    }

    public void setTxtStatus(TextView txtStatus) {
        this.txtStatus = txtStatus;
    }

    public TextView getTxtLevel() {
        return txtLevel;
    }

    public void setTxtLevel(TextView txtLevel) {
        this.txtLevel = txtLevel;
    }

    public TextView getTxtDescription() {
        return txtDescription;
    }

    public void setTxtDescription(TextView txtDescription) {
        this.txtDescription = txtDescription;
    }
}