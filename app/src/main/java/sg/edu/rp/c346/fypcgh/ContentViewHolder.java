package sg.edu.rp.c346.fypcgh;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import java.util.ArrayList;

public class ContentViewHolder extends ChildViewHolder {

    EditText etDrug, etFasting, etAdm;
    CheckBox cbAC, cbNI, cbSC;
    Button btnEdit;
    int counter;
    ArrayList<Content> c1 = new ArrayList<>();
    clickItemListener clickItemListeners;

    public ContentViewHolder(View itemView) {
        super(itemView);

        etDrug = itemView.findViewById(R.id.etDrug);
        etFasting = itemView.findViewById(R.id.etFasting);
        etAdm = itemView.findViewById(R.id.etAdm);
        cbAC = itemView.findViewById(R.id.cbAC);
        cbNI = itemView.findViewById(R.id.cbNI);
        cbSC = itemView.findViewById(R.id.cbSC);
        btnEdit = itemView.findViewById(R.id.btnEdit);
    }

    public ContentViewHolder(View itemView, clickItemListener clickItemListeners) {
        super(itemView);
        this.clickItemListeners = clickItemListeners;
    }

    public void bind(Content c){

        etDrug.setFocusable(false);
        etDrug.setEnabled(false);

        etFasting.setFocusable(false);
        etFasting.setEnabled(false);

        etAdm.setFocusable(false);
        etAdm.setEnabled(false);

        cbAC.setFocusable(false);
        cbAC.setEnabled(false);

        cbNI.setFocusable(false);
        cbNI.setEnabled(false);

        cbSC.setFocusable(false);
        cbSC.setEnabled(false);

        etDrug.setText(c.drug);
        etFasting.setText(c.fast);
        etAdm.setText(c.adm);
        if(c.ac.equals("1")){
            cbAC.setChecked(true);
        }
        else{
            cbAC.setChecked(false);
        }
        if(c.ni.equals("1")){
            cbNI.setChecked(true);
        }
        else{
            cbNI.setChecked(false);
        }
        if(c.sc.equals("1")){
            cbSC.setChecked(true);
        }
        else{
            cbSC.setChecked(false);
        }

        btnEdit.setOnClickListener((v)->{
            if(counter % 2 == 0){
                counter++;
                btnEdit.setBackgroundResource(R.drawable.done);

                etDrug.setFocusableInTouchMode(true);
                etDrug.setEnabled(true);

                etFasting.setFocusableInTouchMode(true);
                etFasting.setEnabled(true);

                cbAC.setFocusable(true);
                cbAC.setEnabled(true);

                cbNI.setFocusable(true);
                cbNI.setEnabled(true);

                cbSC.setFocusable(true);
                cbSC.setEnabled(true);
            }
            else{
                counter++;
                btnEdit.setBackgroundResource(R.drawable.edit);

                String ac;
                String sc;
                String ni;

                if(cbAC.isChecked()){
                    ac = "1";
                }
                else{
                    ac = "0";
                }
                if(cbNI.isChecked()){
                    ni = "1";
                }
                else{
                    ni = "0";
                }
                if(cbSC.isChecked()){
                    sc = "1";
                }
                else {
                    sc = "0";
                }

                c1.add(new Content(etDrug.getText().toString(), etFasting.getText().toString(),
                        etAdm.getText().toString(), ni, ac, sc, c.id, c.queue, c.nric));

                Log.e("HelpMePls", c1.size() + "");

                etDrug.setFocusable(false);
                etDrug.setEnabled(false);

                etFasting.setFocusable(false);
                etFasting.setEnabled(false);

                cbAC.setFocusable(false);
                cbAC.setEnabled(false);

                cbNI.setFocusable(false);
                cbNI.setEnabled(false);

                cbSC.setFocusable(false);
                cbSC.setEnabled(false);

                if(clickItemListeners!=null){
                    this.clickItemListeners.onItemClick(this.c1);
                }

                this.c1.clear();

            }

        });

    }

    interface clickItemListener {
        void onItemClick(ArrayList<Content> a);
    }

}
