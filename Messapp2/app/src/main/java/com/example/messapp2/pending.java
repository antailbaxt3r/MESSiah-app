package com.example.messapp2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link pending.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link pending#newInstance} factory method to
 * create an instance of this fragment.
 */
public class pending extends Fragment {

    ListView pendinglv;
    ArrayList<String> pendingal=new ArrayList<>();
    ArrayAdapter<String> pendingaa;
    DatabaseReference pendRef,onRef,doneRef;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public pending() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment pending.
     */
    // TODO: Rename and change types and number of parameters
    public static pending newInstance(String param1, String param2) {
        pending fragment = new pending();
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
        View v=inflater.inflate(R.layout.fragment_pending, container, false);

        pendingaa=new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,pendingal);
        pendinglv=v.findViewById(R.id.ListViewMessAppPending);
        pendRef=FirebaseDatabase.getInstance().getReference().child("ANC").child("ORDERS");
        Calendar cr=Calendar.getInstance();
        cr.add(Calendar.HOUR,-16);
        String dateinformat=String.valueOf(cr.get(Calendar.YEAR))+"-"+String.valueOf(cr.get(Calendar.MONTH)+1)+"-"+String.valueOf(cr.get(Calendar.DATE));
        pendRef=pendRef.child(dateinformat);
        onRef=pendRef.child("ONGOING");
        pendRef=pendRef.child("PENDING");
        pendinglv.setAdapter(pendingaa);


        pendRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                dataTemplate temporaryDataTemplate=dataSnapshot.getValue(dataTemplate.class);

                String idandorder;
                if(temporaryDataTemplate.packed)
                    idandorder="\n"+temporaryDataTemplate.order_id+"-- TO BE PACKED "+"\n\n"+temporaryDataTemplate.name+"\n\n"+temporaryDataTemplate.order+"\nTotal amount paid=Rs."+String.valueOf(temporaryDataTemplate.paid_Rs)+"\n";
                else
                    idandorder="\n"+temporaryDataTemplate.order_id+"\n\n"+temporaryDataTemplate.name+"\n\n"+temporaryDataTemplate.order+"\nTotal amount paid=Rs."+String.valueOf(temporaryDataTemplate.paid_Rs)+"\n";
                orderData.ordernobject.put(idandorder,temporaryDataTemplate);
                orderData.ordernkey.put(idandorder,dataSnapshot.getKey());
                pendingal.add(idandorder);
                pendingaa.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                dataTemplate temporaryDataTemplate=dataSnapshot.getValue(dataTemplate.class);
                String idandorder;
                if(temporaryDataTemplate.packed)
                    idandorder="\n"+temporaryDataTemplate.order_id+"-- TO BE PACKED "+"\n\n"+temporaryDataTemplate.name+"\n\n"+temporaryDataTemplate.order+"\nTotal amount paid=Rs."+String.valueOf(temporaryDataTemplate.paid_Rs)+"\n";
                else
                    idandorder="\n"+temporaryDataTemplate.order_id+"\n\n"+temporaryDataTemplate.name+"\n\n"+temporaryDataTemplate.order+"\nTotal amount paid=Rs."+String.valueOf(temporaryDataTemplate.paid_Rs)+"\n";
                orderData.ordernobject.put(idandorder,temporaryDataTemplate);
                pendingal.remove(idandorder);
                pendingaa.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        pendinglv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                AlertDialog.Builder adb = new AlertDialog.Builder(getContext());

                final TextView temptextview=(TextView)view;



                adb.setTitle("Transfer order "+temptextview.getText().toString()+"  ?");
                adb.setIcon(android.R.drawable.ic_dialog_alert);
                adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        pendRef.child(orderData.ordernkey.get(temptextview.getText().toString())).removeValue();
                        onRef.push().setValue(orderData.ordernobject.get(temptextview.getText().toString()));
                    }
                });
                adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                adb.show();







            }
        });




        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
