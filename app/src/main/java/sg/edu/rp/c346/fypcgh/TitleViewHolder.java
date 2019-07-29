package sg.edu.rp.c346.fypcgh;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class TitleViewHolder extends GroupViewHolder {
    TextView tvName, tvNric, tvAge, tvWardBed;
    ConstraintLayout constLayout;
    ImageView arrow;


    public TitleViewHolder(View itemView) {
        super(itemView);

        tvName = itemView.findViewById(R.id.tvName1);
        tvAge = itemView.findViewById(R.id.tvAge1);
        tvNric = itemView.findViewById(R.id.tvNric);
        tvWardBed = itemView.findViewById(R.id.tvWardBed);
        constLayout = itemView.findViewById(R.id.constLayout);
        arrow = itemView.findViewById(R.id.arrow);
    }

    public void bind(Title t){
        tvName.setText(t.name);
        tvWardBed.setText("Ward " + t.ward + " - Bed " + t.bed);
        tvNric.setText(t.nric);
        tvAge.setText(t.age);
        if(t.gender.equals("M")){
            tvName.setTextColor(Color.parseColor("#98F1EB"));
        }
        else if (t.gender.equals("F")){
            tvName.setTextColor(Color.parseColor("#FFB1E0"));
        }
        if(t.priority.equals("1")){
            constLayout.setBackgroundColor(Color.parseColor("#DB142F"));
        }
        else if(t.priority.equals("2")){
            constLayout.setBackgroundColor(Color.parseColor("#E94714"));
        }
    }

    @Override
    public void expand() {
        animateExpand();
    }

    @Override
    public void collapse() {
        animateCollapse();
    }

    private void animateExpand() {
        RotateAnimation rotate =
                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.startAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.startAnimation(rotate);
    }
}
