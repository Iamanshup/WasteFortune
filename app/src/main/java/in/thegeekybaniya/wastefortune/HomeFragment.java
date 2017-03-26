package in.thegeekybaniya.wastefortune;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import in.thegeekybaniya.wastefortune.Forms.ProductView;
import in.thegeekybaniya.wastefortune.POJO.Product;

/**
 * Created by Kabir on 21/03/2017.
 */

public class HomeFragment extends Fragment {

    RecyclerView.RecycledViewPool mPool;


    RecyclerView rvLap, rvMobs, rvTv;


    FirebaseDatabase mRoot= FirebaseDatabase.getInstance();

    DatabaseReference mProd= mRoot.getReference().child("products");

    DatabaseReference mLap=mProd.child("laptop");
    DatabaseReference mMob=mProd.child("mobile");

    DatabaseReference mTv=mProd.child("tv");

    FirebaseAuth auth= FirebaseAuth.getInstance();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.home_fragment, container, false);

        rvLap= (RecyclerView) v.findViewById(R.id.rvLaps);
        rvMobs= (RecyclerView) v.findViewById(R.id.rvMobs);
        rvTv= (RecyclerView) v.findViewById(R.id.rvTv);


        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager MyLayoutManager1 = new LinearLayoutManager(getActivity());
        MyLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager MyLayoutManager2 = new LinearLayoutManager(getActivity());
        MyLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);




        MyLayoutManager.setRecycleChildrenOnDetach(true);







        final FirebaseRecyclerAdapter<Product, ProductHolder> lapAdapter= new FirebaseRecyclerAdapter<Product, ProductHolder>(
                Product.class,
                R.layout.list_product_view,
                ProductHolder.class,
                mLap
        ) {
            @Override
            protected void populateViewHolder(ProductHolder viewHolder, Product model, int position) {

                if (auth.getCurrentUser()==null){
                    viewHolder.tvPrice.setText(String.valueOf(model.getPrice()+(model.getPrice()*0.1)));


                }else {
                    viewHolder.tvPrice.setText(String.valueOf(model.getPrice()));

                }

                viewHolder.tvName.setText(String.valueOf(model.getName()));


            }
        };


        final FirebaseRecyclerAdapter<Product, ProductHolder> mobAdapter= new FirebaseRecyclerAdapter<Product, ProductHolder>(
                Product.class,
                R.layout.list_product_view,
                ProductHolder.class,
                mMob
        ) {
            @Override
            protected void populateViewHolder(ProductHolder viewHolder, Product model, int position) {
                if (auth.getCurrentUser()==null){
                    viewHolder.tvPrice.setText(String.valueOf(model.getPrice()+(model.getPrice()*0.1)));


                }else {
                    viewHolder.tvPrice.setText(String.valueOf(model.getPrice()));

                }
                viewHolder.tvName.setText(String.valueOf(model.getName()));


            }
        };


        final FirebaseRecyclerAdapter<Product, ProductHolder> tvAdapter= new FirebaseRecyclerAdapter<Product, ProductHolder>(
                Product.class,
                R.layout.list_product_view,
                ProductHolder.class,
                mTv
        ) {
            @Override
            protected void populateViewHolder(ProductHolder viewHolder, Product model, int position) {
                if (auth.getCurrentUser()==null){
                    viewHolder.tvPrice.setText(String.valueOf(model.getPrice()+(model.getPrice()*0.1)));


                }else {
                    viewHolder.tvPrice.setText(String.valueOf(model.getPrice()));

                }
                viewHolder.tvName.setText(String.valueOf(model.getName()));


            }
        };
        if (mPool != null) {
            rvLap.setRecycledViewPool(mPool);
            rvMobs.setRecycledViewPool(mPool);
            rvTv.setRecycledViewPool(mPool);
            rvLap.setItemViewCacheSize(10);
            rvMobs.setItemViewCacheSize(10);
            rvTv.setItemViewCacheSize(10);




            Log.i("@@@", "using view pool :" + mPool);
        }

        rvLap.setLayoutManager(MyLayoutManager);
        rvLap.setAdapter(lapAdapter);

        rvMobs.setLayoutManager(MyLayoutManager1);
        rvMobs.setAdapter(mobAdapter);

        rvTv.setLayoutManager(MyLayoutManager2);
        rvTv.setAdapter(tvAdapter);






        rvLap.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {


                        if(FirebaseAuth.getInstance().getCurrentUser()==null){
                            Toast.makeText(getContext(), "Sign in First", Toast.LENGTH_SHORT).show();

                        }else{
                            Product prod=mobAdapter.getItem(position);
                            Intent i =new Intent(getActivity(), ProductView.class);

                            i.putExtra("name", prod.getName());
                            i.putExtra("desc", prod.getDescription());
                            i.putExtra("price", String.valueOf(prod.getPrice()));
                            i.putExtra("key", prod.getKey());
                            i.putExtra("seller", prod.getSellerEnEmail());

                            startActivity(i);
                        }





                    }
                })
        );

        rvTv.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {


                        if(FirebaseAuth.getInstance().getCurrentUser()==null){
                            Toast.makeText(getContext(), "Sign in First", Toast.LENGTH_SHORT).show();

                        }else{
                            Product prod=mobAdapter.getItem(position);
                            Intent i =new Intent(getActivity(), ProductView.class);

                            i.putExtra("name", prod.getName());
                            i.putExtra("desc", prod.getDescription());
                            i.putExtra("price", String.valueOf(prod.getPrice()));
                            i.putExtra("key", prod.getKey());
                            i.putExtra("seller", prod.getSellerEnEmail());

                            startActivity(i);
                        }





                    }
                })
        );

        rvMobs.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {


                       if(FirebaseAuth.getInstance().getCurrentUser()==null){
                           Toast.makeText(getContext(), "Sign in First", Toast.LENGTH_SHORT).show();

                       }else{
                           Product prod=mobAdapter.getItem(position);
                           Intent i =new Intent(getActivity(), ProductView.class);

                           i.putExtra("name", prod.getName());
                           i.putExtra("desc", prod.getDescription());
                           i.putExtra("price", String.valueOf(prod.getPrice()));
                           i.putExtra("key", prod.getKey());
                           i.putExtra("seller", prod.getSellerEnEmail());

                           startActivity(i);
                       }





                    }
                })
        );



        // Inflate the layout for this fragment
        return v;
    }


    public static class ProductHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice;
        public ProductHolder(View itemView) {
            super(itemView);

            tvName= (TextView) itemView.findViewById(R.id.name);

            tvPrice= (TextView) itemView.findViewById(R.id.price);


        }
    }


    static class PageAdapter extends FragmentPagerAdapter {
        RecyclerView.RecycledViewPool mPool = new RecyclerView.RecycledViewPool(){
            @Override
            public RecyclerView.ViewHolder getRecycledView(int viewType) {
                RecyclerView.ViewHolder scrap = super.getRecycledView(viewType);
                Log.i("@@@", "get holder from pool: "+scrap );
                return scrap;
            }

            @Override
            public void putRecycledView(RecyclerView.ViewHolder scrap) {
                super.putRecycledView(scrap);
                Log.i("@@@", "put holder to pool: " + scrap);
            }

            @Override
            public String toString() {
                return "ViewPool in adapter@"+Integer.toHexString(hashCode());
            }
        };
        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            HomeFragment f = new HomeFragment();
            f.mPool = mPool;
            return f;
        }

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "pos:"+position;
        }
    }
}

