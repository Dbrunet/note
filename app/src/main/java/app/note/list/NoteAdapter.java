package app.note.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.note.R;
import app.note.data.db.entity.Note;
import app.note.utils.Util;

/**
 * Classe de controle dos itens da lista (Tela inicial)
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private List<Note> mValues;
    private ListContract.OnItemClickListener mOnItemClickListener;

    public NoteAdapter(ListContract.OnItemClickListener onItemClickListener) {
        mValues = new ArrayList<>();
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //cria a view de cada caixinha
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new ViewHolder(view);
    }

    /**
     * varre a lista de objetos recebidos pelo metodo {@link #setValues(List)}
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //recebe por posicao
        holder.mItem = mValues.get(position);

        //seta o valor de name no campo de texto nameTextView
        holder.nameTextView.setText(holder.mItem.name);
        //seta o valor de tag no campo de texto tagTextView
        holder.tagTextView.setText(holder.mItem.tag);

        //seta o valor de description no campo de texto descriptionTextView
        holder.descriptionTextView.setText(holder.mItem.description);

        //seta o valor de dateAlert no campo de texto dataAlertTextView
        holder.dataAlertTextView.setText("Lembrete: " + Util.formatMin(holder.mItem.dateAlert));

        //seta a cor no fundo da caixa
        holder.mView.setBackgroundColor(Integer.parseInt(holder.mItem.color));

        //evendo de clique da para transição de tela
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.clickItem(holder.mItem);
            }
        });

        //evento de longo click da caixa para remoção
        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mOnItemClickListener.clickLongItem(holder.mItem);
                return false;
            }
        });
    }

    //total de itens
    @Override
    public int getItemCount() {
        return mValues.size();
    }

    //recebe todos os lembretes cadastrados no banco
    public void setValues(List<Note> values) {
        mValues = values;
        notifyDataSetChanged();
    }

    //ViewHolder inicializa os componentes do xml
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView nameTextView;
        public final TextView descriptionTextView;
        public final TextView dataAlertTextView;
        public final TextView tagTextView;
        public Note mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            nameTextView = (TextView) view.findViewById(R.id.nameTextView);
            descriptionTextView = (TextView) view.findViewById(R.id.descriptionTextView);
            dataAlertTextView = (TextView) view.findViewById(R.id.dataAlertTextView);
            tagTextView = (TextView) view.findViewById(R.id.tagTextView);
        }
    }


}
