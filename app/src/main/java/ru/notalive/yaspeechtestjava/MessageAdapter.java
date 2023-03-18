package ru.notalive.yaspeechtestjava;

import static ru.notalive.yaspeechtestjava.MESSAGE_TYPE.ANSWER;
import static ru.notalive.yaspeechtestjava.MESSAGE_TYPE.QUESTION;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private final List<Message> messages;
    private final LayoutInflater inflater;


    public MessageAdapter(ChatFragment context){
        this.inflater = LayoutInflater.from(context.getContext());
        messages = new ArrayList<Message>();
    }

    @Override
    public int getItemViewType(int position) {
        return messages.get(position).getType();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case ANSWER:
                view = inflater.inflate(R.layout.chat_item_answer, parent, false);
                break;
            case QUESTION:
                view = inflater.inflate(R.layout.chat_item_question, parent, false);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + viewType);
        }
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.textView.setText(message.getText());
    }

    public void addMessage(Message message){
        messages.add(message);
        notifyItemInserted(getItemCount()-1);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
    public void changeMessage(int id, String message){
        messages.get(id).setText(message);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.textView);
        }
    }
}