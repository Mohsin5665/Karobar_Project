package com.vcodewise.karobarlist.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.vcodewise.karobarlist.MyApplication;
import com.vcodewise.karobarlist.R;
import com.vcodewise.karobarlist.adapters.Categories_Adapter;
import com.vcodewise.karobarlist.adapters.CustomHireAdapter;
import com.vcodewise.karobarlist.models.BusinessItem;
import com.vcodewise.karobarlist.models.Categories_list;
import com.vcodewise.karobarlist.models.CategoryItem;
import com.vcodewise.karobarlist.utils.ClickableViewPager;
import com.vcodewise.karobarlist.utils.DataHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int selectedWantedItem = 0;
    private static final String URL_data = "http://karobarlist.xtechnos.com/api/categories?active=1&offset=0&limit=10&all=1";
    private static final String URL_Web = "http://karobarlist.xtechnos.com/api/businesses?featured=1";
    private static final String RUL_Cate = "http://karobarlist.xtechnos.com/api/featuredCategories";
    private RecyclerView rv;
    private List<Categories_list> listitem;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ImageView leftArrow, rightArrow;
    private ClickableViewPager mGalleryPager;
    Context context;
    private  TextView textView;
    private Spinner spinner;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        leftArrow = (ImageView) view.findViewById(R.id.leftArrow);
        rightArrow = (ImageView) view.findViewById(R.id.rightArrow);
        leftArrow.setOnClickListener(this);
        rightArrow.setOnClickListener(this);

        rv = (RecyclerView) (RecyclerView) view.findViewById(R.id.catagoryrv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new GridLayoutManager(context, 3));
        listitem = new ArrayList<>();
        textView = (TextView)view.findViewById(R.id.spinner);

        spinner = (Spinner)view.findViewById(R.id.spinner_size);

        loadRecyclerViewData();


        RequestQueue r_queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.GET, RUL_Cate, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject jsonObject = null;

                try {
                    jsonObject = new JSONObject(response);
                    JSONObject data = jsonObject.getJSONObject("data");
                    JSONArray f_categories = data.getJSONArray("categories");

                    if (f_categories.length() > 0) {

                        mGalleryPager = (ClickableViewPager) view.findViewById(R.id.gallery_pager);

                        for (int i = 0; i < f_categories.length(); i++) {

                            JSONObject category = f_categories.getJSONObject(i);

                            CategoryItem item = new CategoryItem();
                            item.setImageUrl(category.getString("image"));
                            item.setNoofItems(category.getString("total_businesses"));
                            item.setTitle(category.getString("name"));
                            DataHolder.getInstance().getCategoryItemList().add(item);

                        }

                        selectedWantedItem = 0;
                        mGalleryPager.setAdapter(new ImagePagerAdapter(getActivity(), DataHolder.getInstance().getCategoryItemList()));


                        mGalleryPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                                selectedWantedItem = position;
                            }

                            @Override
                            public void onPageSelected(int position) {
                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });

                        mGalleryPager.setBackground(null);
                        mGalleryPager.setOnItemClickListener(new ClickableViewPager.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {

                            }
                        });
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        r_queue.add(request);

//        for (int i = 0; i < 5; i++) {
//            CategoryItem item = new CategoryItem();
//            item.setImageUrl("http://karobarlist.xtechnos.com/storage/app/public/featured_category/hXvYkv1SvGFVA9joBniBPjkTuhXy3MTcziqjuLzq.jpeg");
//            item.setNoofItems(i + "");
//            item.setTitle("Item #" + i + 1);
//            DataHolder.getInstance().getCategoryItemList().add(item);
//        }
//        selectedWantedItem = 0;
//        mGalleryPager.setAdapter(new ImagePagerAdapter(getActivity(), DataHolder.getInstance().getCategoryItemList()));
//
//        mGalleryPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                selectedWantedItem = position;
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//
//        mGalleryPager.setBackground(null);
//        mGalleryPager.setOnItemClickListener(new ClickableViewPager.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//
//            }
//        });


        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_Web,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONObject jsonObject = null;

                        try {
                            jsonObject = new JSONObject(response);
                            JSONObject data = jsonObject.getJSONObject("data");
                            JSONArray businesses = data.getJSONArray("businesses");

                            if (businesses.length() > 0) {

                                for (int i = 0; i < businesses.length(); i++) {

                                    JSONObject business = businesses.getJSONObject(i);

                                    BusinessItem item = new BusinessItem();
                                    item.getImagesList().add(business.getString("image"));
                                    item.setItemId(business.getInt("id"));
                                    item.setCategoryName(business.getString("category"));
                                    item.setName(business.getString("title"));
                                    item.setAddress(business.getString("address"));
                                    if (business.getString("mobile") != null && business.getString("mobile").equals("null")) {
                                        item.setNumber(" ");

                                    } else {
                                        item.setNumber(business.getString("mobile"));
                                    }
//                                    item.setRating(business.getLong("review_ratings"));
//                                    item.setNoofRating(business.getInt("total_reviews"));

                                    item.setRating(i + 1.0f);
                                    item.setNoofRating(i + 2);
                                    item.setNoofReviews(i + 3);
                                    item.setFav(i / 2 == 0);
                                    DataHolder.getInstance().getBusinessItemList().add(item);

                                }

                                CustomHireAdapter mCustomListAdapter = new CustomHireAdapter(getActivity(), DataHolder.getInstance().getBusinessItemList());
                                mRecyclerView.setAdapter(mCustomListAdapter);
                            }

                            Log.d("f_tag", businesses.toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("f_tag", "That didn't work!");
            }
        });
        queue.add(stringRequest);
//        Log.d("f_tag", "after!!!");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.leftArrow:
                if (selectedWantedItem > 0) {
                    mGalleryPager.setCurrentItem(selectedWantedItem - 1);
                }
                break;
            case R.id.rightArrow:
                if (selectedWantedItem < DataHolder.getInstance().getCategoryItemList().size() - 1) {
                    mGalleryPager.setCurrentItem(selectedWantedItem + 1);
                }
                break;
            default:
                break;
        }
    }

    private static class ImagePagerAdapter extends PagerAdapter {

        private Context mContext;
        private List<CategoryItem> categoryItemList;
        private LayoutInflater mLayoutInflater;
        private DisplayImageOptions mDisplayImageOptions;

        public ImagePagerAdapter(Context context, List<CategoryItem> categoryItemList) {
            mContext = context;
            this.categoryItemList = categoryItemList;
            mLayoutInflater = LayoutInflater.from(context);

            mDisplayImageOptions = new DisplayImageOptions.Builder()
                    .resetViewBeforeLoading(true)
                    .cacheOnDisk(true)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .considerExifParams(true)
                    .displayer(new FadeInBitmapDisplayer(300))
                    .build();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return categoryItemList.size();
        }

        @Override
        public Object instantiateItem(ViewGroup viewGroup, int position) {
            View view = mLayoutInflater.inflate(R.layout.item_wanted, viewGroup, false);

            ImageView imageView = (ImageView) view.findViewById(R.id.wantedimageview);
            TextView txtNoofItems = (TextView) view.findViewById(R.id.noofitems);
            TextView txtTitle = (TextView) view.findViewById(R.id.categorytitle);
            CategoryItem item = categoryItemList.get(position);

            MyApplication.imageLoader.displayImage(item.getImageUrl(), imageView, mDisplayImageOptions, new SimpleImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                  //  holder.mSpinner.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    String message = null;
                    switch (failReason.getType()) {
                        case IO_ERROR:
                            message = "Input/Output error";
                            break;
                        case DECODING_ERROR:
                            message = "Image can't be decoded";
                            break;
                        case NETWORK_DENIED:
                            message = "Downloads are denied";
                            break;
                        case OUT_OF_MEMORY:
                            message = "Out Of Memory error";
                            break;
                        case UNKNOWN:
                            message = "Unknown error";
                            break;
                    }
                    // holder.mSpinner.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    // holder.mSpinner.setVisibility(View.GONE);
                }
            });
            txtTitle.setText(item.getTitle());
            txtNoofItems.setText(item.getNoofItems());

            viewGroup.addView(view, 0);
            return view;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }
    }

    private void loadRecyclerViewData() {


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_data,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject data = jsonObject.getJSONObject("data");
                            JSONArray array = data.getJSONArray("categories");

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject o = array.getJSONObject(i);
                                Categories_list list = new Categories_list(
                                        o.getString("name"),
                                        o.getString("image")

                                );
                                listitem.add(list);
                            }

                            Categories_Adapter adapter = new Categories_Adapter(listitem, SearchFragment.this);
                            rv.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_LONG).show();
//                Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);
    }
}