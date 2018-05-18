package fr.hcube_conseil.hcube_conseil.modele;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import fr.hcube_conseil.hcube_conseil.R;

public class SliderAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;

    /**
     * Constructeur
     * @param context
     */
    public SliderAdapter(Context context){
        this.mContext = context;
    }

    //Array

    public int[] slide_image = {
            R.drawable.ic_logo_hcube,
            R.drawable.ic_slide_2_2,
            R.drawable.ic_slide_3
    };
    public String[] slide_heading = {
            "Hcube Conseil",
            "Entreprise",
            "Talent",
    };
    public String[] slide_desc = {
            "Hcube vous conseil et vous accompagne sur vos projet, et même dans le bus grace a notre application ! Decouvrez hcube a travers les differentes rubrique !",
            "Vous êtes une entreprise ? Déposer vos offres et trouvez parmis nos talent la perle rare ! Découvrez également les projet de Hcube en cours et a venir !",
            "Vous êtes à la recherche d'un emplois au top ? Parcourez les offres des différentes entreprise et trouvez chaussure a votre pied !"
    };

    /**
     *Getter
     * @return
     */
    @Override
    public int getCount() {
        return slide_heading.length;
    }

    /**
     *
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject( View view, Object object) {
        return view == (RelativeLayout) object;
    }

    /**
     * Constructeur de l'objet
     * @param container
     * @param position
     * @return
     */
    public Object instantiateItem(ViewGroup container, int position){
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View view = mLayoutInflater.inflate(R.layout.slide_layout,container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeadingView = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescView = (TextView) view.findViewById(R.id.slide_desc);
        RelativeLayout slideBackgroundView = (RelativeLayout) view.findViewById(R.id.rl_background_slider);

        slideImageView.setImageResource(slide_image[position]);
        slideHeadingView.setText(slide_heading[position]);
        slideDescView.setText(slide_desc[position]);

        container.addView(view);
        return view;
    }

    /**
     * Arrete le processus a la derniere image
     * @param container
     * @param position
     * @param object
     */
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((RelativeLayout)object);
    }
}



















