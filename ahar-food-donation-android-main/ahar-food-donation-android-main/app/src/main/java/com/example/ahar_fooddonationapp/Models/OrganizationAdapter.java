package com.example.ahar_fooddonationapp.Models;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ahar_fooddonationapp.R;

import java.util.List;
//
public class OrganizationAdapter extends RecyclerView.Adapter<OrganizationAdapter.OrganizationHolder> {
    private Context context;
    private List<Organization> orgList;

    public OrganizationAdapter(Context context, List<Organization> movieList) {
        this.context = context;
        this.orgList = movieList;
    }
    @NonNull
    @Override
    public OrganizationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.item , parent , false);
        return new OrganizationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrganizationHolder holder, int position) {
        Organization org= orgList.get(position);
        holder.location.setText(org.getOrganizationLocation().toString());
        holder.name.setText(org.getOrganizationName().toString());
        holder.email.setText(org.getOrganizationEmail().toString());
        holder.phone.setText ( org.getOrganizationPhone().toString());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return orgList.size();
    }
//    @Override
//    public void onBindViewHolder(@NonNull OrganizationHolder holder, int position) {
//
//        Organization movie = movieList.get(position);
//        holder.rating.setText(movie.getRating().toString());
//        holder.title.setText(movie.getTitle());
//        holder.overview.setText(movie.getOverview());
//        Glide.with(context).load(movie.getPoster()).into(holder.imageView);
//
//        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context , DetailActivity.class);
//
//                Bundle bundle = new Bundle();
//                bundle.putString("title" , movie.getTitle());
//                bundle.putString("overview" , movie.getOverview());
//                bundle.putString("poster" , movie.getPoster());
//                bundle.putDouble("rating" , movie.getRating());
//
//                intent.putExtras(bundle);
//
//                context.startActivity(intent);
//            }
//        });
//
//    }
public class OrganizationHolder extends RecyclerView.ViewHolder{
    TextView email,phone,location,name;
    ConstraintLayout constraintLayout;

    public OrganizationHolder(@NonNull View itemView) {
        super(itemView);
        email=itemView.findViewById(R.id.item_emailTV);
        phone=itemView.findViewById(R.id.item_phoneTV);
        name=itemView.findViewById(R.id.item_nameTV);
        location=itemView.findViewById(R.id.item_locationTV);
        constraintLayout=itemView.findViewById(R.id.main_layoutCL);
    }
}

}
