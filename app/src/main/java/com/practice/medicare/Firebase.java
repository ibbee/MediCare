package com.practice.medicare;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class Firebase implements DAOInterface{
    public interface DataObserver{
        public void update();
    }
    private DataObserver observer;
    FirebaseDatabase database;
    DatabaseReference referenceusers;
    DatabaseReference referencecart;
    DatabaseReference referenceorderplace;

    ArrayList<Hashtable<String,String>> data_users;
    ArrayList<Hashtable<String,String>> data_cart;
    ArrayList<Hashtable<String,String>> data_orderplace;

    public Firebase(DataObserver ob){
        observer=ob;
        referencecart=database.getReference("cart");
        referenceusers=database.getReference("users");
        referenceorderplace=database.getReference("orderplace");

        referenceusers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    data_users = new ArrayList<Hashtable<String,String>>();
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        GenericTypeIndicator<HashMap<String,Object>> type = new GenericTypeIndicator<HashMap<String, Object>>() {};
                        HashMap<String,Object> map =  d.getValue(type);

                        Hashtable<String,String> obj = new Hashtable<String,String>();
                        for(String key : map.keySet()){
                            obj.put(key,map.get(key).toString());
                        }
                        data_users.add(obj);
                    }
                    observer.update();
                }
                catch (Exception ex) {
                    Log.e("firebasedb", ex.getMessage());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("firebasedb", "Failed to read value.", error.toException());
            }
        });
        referencecart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    data_cart = new ArrayList<Hashtable<String,String>>();
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        GenericTypeIndicator<HashMap<String,Object>> type = new GenericTypeIndicator<HashMap<String, Object>>() {};
                        HashMap<String,Object> map =  d.getValue(type);

                        Hashtable<String,String> obj = new Hashtable<String,String>();
                        for(String key : map.keySet()){
                            obj.put(key,map.get(key).toString());
                        }
                        data_cart.add(obj);
                    }
                    observer.update();
                }
                catch (Exception ex) {
                    Log.e("firebasedb", ex.getMessage());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("firebasedb", "Failed to read value.", error.toException());
            }
        });
        referenceorderplace.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    data_orderplace = new ArrayList<Hashtable<String,String>>();
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        GenericTypeIndicator<HashMap<String,Object>> type = new GenericTypeIndicator<HashMap<String, Object>>() {};
                        HashMap<String,Object> map =  d.getValue(type);

                        Hashtable<String,String> obj = new Hashtable<String,String>();
                        for(String key : map.keySet()){
                            obj.put(key,map.get(key).toString());
                        }
                        data_orderplace.add(obj);
                    }
                    observer.update();
                }
                catch (Exception ex) {
                    Log.e("firebasedb", ex.getMessage());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("firebasedb", "Failed to read value.", error.toException());
            }
        });

    }

    @Override
    public void register(String username, String email, String password) {
        Hashtable<String, String> attributes =new Hashtable<>();
        attributes.put("username",username);
        attributes.put("email",email);
        attributes.put("password",password);
        referenceusers.child(username).setValue(attributes);
    }

    @Override
    public int login(String username, String password) {
        if(data_users!=null) {
            for (Hashtable<String, String> obj2 : data_users) {
                if (obj2.get("username").equals(username)&&obj2.get("password").equals(password))
                    return 1;
            }
        }
        return 0;
    }

    @Override
    public void addCart(String username, String product, float price, String otype) {
        Hashtable<String, String> attributes =new Hashtable<>();
        attributes.put("username",username);
        attributes.put("product",product);
        attributes.put("price",Float.toString(price));
        attributes.put("otype",otype);
        referenceusers.child(username).child(product).setValue(attributes);
    }

    @Override
    public int checkCart(String username, String product) {
        if(data_cart!=null)
        {
            for (Hashtable<String, String> obj2 : data_cart) {
                if (obj2.get("username").equals(username)&&obj2.get("product").equals(product))
                    return 1;
            }
        }
        return 0;
    }

    @Override
    public void removeCart(String username, String product) {
        referencecart.child(username).child(product).removeValue();
    }

    @Override
    public ArrayList getCartData(String username, String otype) {
        if(data_cart==null)
        {
            data_cart=new ArrayList<>();
        }
        return data_cart;
    }

    @Override
    public void addOrder(String username, String fullname, String address, String contact, int pincode, String date, String time, float price, String otype) {
        Hashtable<String, String> attributes =new Hashtable<>();
        attributes.put("id",Integer.toString(data_orderplace.size()));
        attributes.put("username",username);
        attributes.put("fullname",fullname);
        attributes.put("address",address);
        attributes.put("contactno",contact);
        attributes.put("pincode",Integer.toString(pincode));
        attributes.put("date",date);
        attributes.put("time",time);
        attributes.put("amount",Float.toString(price));
        attributes.put("otype",otype);
        referenceusers.child(Integer.toString(data_orderplace.size())).setValue(attributes);
    }

    @Override
    public ArrayList getOrderData(String username) {
        if(data_orderplace==null)
            data_orderplace=new ArrayList<>();
        return data_orderplace;
    }

    @Override
    public int checkAppointmentExists(String username, String fullname,String otype, String address, String contact, String date, String time) {
        if(data_orderplace!=null)
        {
            for (Hashtable<String, String> obj2 : data_orderplace) {
                if (obj2.get("username").equals(username)&&obj2.get("otype").equals(otype)&&obj2.get("date").equals(date)&&obj2.get("time").equals(time))
                    return 1;
            }
        }
        return 0;
    }
}
