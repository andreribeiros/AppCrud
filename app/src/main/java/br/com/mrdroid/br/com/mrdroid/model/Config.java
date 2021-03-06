package br.com.mrdroid.br.com.mrdroid.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

public class Config extends AppCompatActivity {

    private int porta;
    private String ip;

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Config(String porta, String ip) throws Exception{
        this.ip = ip;
        try{
            this.porta = Integer.parseInt(porta);
        }catch (Exception e){
            throw  new Exception("A porta deve ser um numero");

        }
    }

    public Config(){

    }


    public void getShaed(){
        SharedPreferences sh = getSharedPreferences("config", Context.MODE_PRIVATE);
        Config conf = new Config();
        this.setIp(sh.getString("ip",""));
        this.setPorta(sh.getInt("porta",0));
    }
}
