package com.example.labsheet11;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Comment;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    ArrayList subList;
    ArrayList messageList;
    Context context;

    public MessageAdapter(ArrayList subList, ArrayList messageList, Context context) {
        this.subList = subList;
        this.messageList = messageList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_messages,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return  viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.subject.setText(subList.get(position).toString());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.layout.getContext(),Message.class);
                intent.putExtra("subject",subList.get(position).toString());
                intent.putExtra("message",messageList.get(position).toString());
                holder.layout.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return subList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
TextView subject;
RelativeLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subject=itemView.findViewById(R.id.MessageView);
            layout=itemView.findViewById(R.id.parentLayout);
        }
    }
}
