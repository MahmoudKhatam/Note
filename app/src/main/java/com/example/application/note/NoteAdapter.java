package com.example.application.note;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private List<notes> mynote = new ArrayList<>();
    private onItemClickListener listener;

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);

        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        notes currentNote = mynote.get(position);
        holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewDescirption.setText(currentNote.getDesciption());
        holder.textViewPriority.setText(String.valueOf(currentNote.getPriority()));

    }

    @Override
    public int getItemCount() {
        return mynote.size();
    }

    public void setNotes(List<notes> mynote) {
        this.mynote = mynote;
        notifyDataSetChanged();
    }

    public notes getNoteAt(int position) {
        return mynote.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescirption;
        private TextView textViewPriority;


        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textView_title);
            textViewDescirption = itemView.findViewById(R.id.textView_descripition);
            textViewPriority = itemView.findViewById(R.id.textView_priorty);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {

                        listener.onItemClick(mynote.get(position));
                    }
                }
            });
        }

    }

    public interface onItemClickListener {
        void onItemClick(notes note);
    }

    public void setOnItemClickListener(onItemClickListener Listener) {
        this.listener = Listener;
    }
}
