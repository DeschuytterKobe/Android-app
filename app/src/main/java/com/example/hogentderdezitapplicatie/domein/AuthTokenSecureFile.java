package com.example.hogentderdezitapplicatie.domein;


import com.auth0.android.result.Credentials;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AuthTokenSecureFile implements SecureFile {


    private final  String _tokenName ="jwt_tokens_v3.json";


    private Date expiresAt;
    private String accesToken;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private String idToken;
    private String refreshToken;
    private int userId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    private String tokenType;




    public AuthTokenSecureFile(){

    }

//    public AuthTokenSecureFile getCredentials(){
//        return  new AuthTokenSecureFile(jwtToken) ;
//    }

    public void fill(Credentials credentials, int userId) {
        setAccesToken(credentials.getAccessToken());
        setIdToken(credentials.getIdToken());
        setRefreshToken(credentials.getRefreshToken());
        setExpiresAt(credentials.getExpiresAt());
        setTokenType(credentials.getType());
        fillCustomContent(getIdToken());
        setUserId(userId);

    }


    private final String KEY_EMAIL_ADRESS ="name";
    private final String KEY_EMAIL_NAME ="nickname";

    private  void fillCustomContent(String token){
        if(token == null || token.isEmpty()){
            throw   new IllegalArgumentException("id token should be filled in");
        }

        String dataBase64 = token.split("\\.")[1];
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(dataBase64));


        Map<String, Object> retMap = new Gson().fromJson(
                payload, new TypeToken<HashMap<String, Object>>() {}.getType()
        );

        //TODO fix error handeling in try catch en zo
        String result =retMap.get(KEY_EMAIL_ADRESS).toString();
        setEmail(result);
        setName(retMap.get(KEY_EMAIL_NAME).toString());



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
        return AuthTokenSecureFile.class;
    }


}