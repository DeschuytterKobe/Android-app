package com.example.hogentderdezitapplicatie.domein;


import com.auth0.android.result.Credentials;

import java.lang.reflect.Type;
import java.util.Date;

public class AuthTokenSecureFile implements SecureFile {


    private final  String _tokenName ="jwt_tokens_v1.json";


    private Date expiresAt;
    private String accesToken;
    private String idToken;
    private String refreshToken;
//    private String type;



    public AuthTokenSecureFile(){

    }

//    public AuthTokenSecureFile getCredentials(){
//        return  new AuthTokenSecureFile(jwtToken) ;
//    }

    public void fill(Credentials credentials) {
        setAccesToken(credentials.getAccessToken());
        setIdToken(credentials.getIdToken());
        setRefreshToken(credentials.getRefreshToken());
        setExpiresAt(credentials.getExpiresAt());
//        setType(credentials.getType());
    }



    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public void setAccesToken(String accesToken) {
        this.accesToken = accesToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

//    public void setType(String type) {
//        this.type = type;
//    }
    public String getAccesToken() {
        return accesToken;
    }
    public Date getExpiresAt() {
        return expiresAt;
    }
    public String getIdToken() {
        return idToken;
    }
    public String getRefreshToken() {
        return refreshToken;
    }
//    public String getType() {
//        return type;
//    }




    @Override
    public boolean isValid() {
        //check if token is valid
        return false;
    }
    @Override
    public String getFileName() {
        return _tokenName;
    }

    @Override
    public Type getType() {
        return null;
    }


}